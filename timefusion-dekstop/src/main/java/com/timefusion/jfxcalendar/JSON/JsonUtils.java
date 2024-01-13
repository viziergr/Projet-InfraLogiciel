package com.timefusion.jfxcalendar.JSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonUtils {

  public static final String JSON_FILENAME =
    "timefusion-dekstop/src/main/java/com/timefusion/jfxcalendar/JSON/LocalStorage.json";

  private static final Gson gson = new GsonBuilder()
    .setPrettyPrinting()
    .create();

  public static void printJson() {
    try {
      Path filePath = Path.of(JSON_FILENAME);
      byte[] jsonData = Files.readAllBytes(filePath);
      System.out.println(new String(jsonData));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static JsonElement readJsonPart(String partName) {
    try {
      Path filePath = Path.of(JSON_FILENAME);
      byte[] jsonData = Files.readAllBytes(filePath);
      String jsonString = new String(jsonData);
      JsonElement jsonElement = JsonParser.parseString(jsonString);

      if (jsonElement.isJsonObject()) {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        if (jsonObject.has(partName)) {
          return jsonObject.get(partName);
        }
      } else if (jsonElement.isJsonArray()) {
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        for (JsonElement element : jsonArray) {
          JsonObject jsonObject = element.getAsJsonObject();
          if (jsonObject.has(partName)) {
            return jsonObject.get(partName);
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static String convertObjectToJson(Object object) {
    return gson.toJson(object);
  }

  public static void addEntityArray(
    String filePath,
    String entityName,
    JsonObject newEntity
  ) {
    JsonArray jsonArray = readFromFile(filePath);

    JsonObject jsonRoot = getOrCreateEntityArray(jsonArray, entityName);

    jsonRoot.getAsJsonArray(entityName).add(newEntity);

    writeToFile(jsonArray, filePath);
  }

  public static void addEntityElement(
    String filePath,
    String entityName,
    JsonObject newEntity,
    int position
  ) {
    JsonArray jsonArray = readFromFile(filePath);
    JsonObject jsonRoot = null;

    if (jsonArray.isJsonArray()) {
      jsonRoot = jsonArray.get(position).getAsJsonObject();
    } else {
      System.out.println("Unsupported JSON structure.");
      return;
    }

    if (jsonRoot.has(entityName)) {
      jsonRoot.add(entityName, newEntity);
    } else {
      JsonObject informationObject = jsonRoot.getAsJsonObject(entityName);
      if (informationObject == null) {
        informationObject = new JsonObject();
        jsonRoot.add(entityName, informationObject);
      }
      informationObject.add(entityName, newEntity);
    }

    writeToFileElement(jsonArray, filePath);
  }

  public static void updateEntityArray(
    String filePath,
    String entityName,
    int entityId,
    JsonObject updatedEntity
  ) {
    JsonArray jsonArray = readFromFile(filePath);

    JsonObject jsonRoot = getEntityArray(jsonArray, entityName);
    if (jsonRoot == null) {
      System.out.println("Entity " + entityName + " not found.");
      return;
    }

    JsonArray entityArray = jsonRoot.getAsJsonArray(entityName);
    for (JsonElement element : entityArray) {
      JsonObject entity = element.getAsJsonObject();
      if (entity.has("id") && entity.get("id").getAsInt() == entityId) {
        entityArray.remove(element);
        entityArray.add(updatedEntity);
        break;
      }
    }

    writeToFile(jsonArray, filePath);
  }

  public static void updateEntityElement(
    String filePath,
    String entityName,
    JsonObject updatedEntity
  ) {
    JsonArray jsonArray = readFromFile(filePath);
    JsonObject jsonRoot = null;
    JsonElement jsonElement = jsonArray.get(0);

    if (jsonElement.isJsonObject()) {
      jsonRoot = jsonElement.getAsJsonObject();
    } else {
      System.out.println("Unsupported JSON structure.");
      return;
    }

    if (jsonRoot.has(entityName)) {
      // Retrieve the existing entity
      JsonObject existingEntity = jsonRoot.getAsJsonObject(entityName);

      // Update properties of the existing entity with the new values
      for (String property : updatedEntity.keySet()) {
        existingEntity.add(property, updatedEntity.get(property));
      }
    } else {
      System.out.println("Entity " + entityName + " not found.");
    }

    writeToFileElement(jsonArray, filePath);
  }

  public static void deleteEntityArray(
    String filePath,
    String entityName,
    int entityId
  ) {
    JsonArray jsonArray = readFromFile(filePath);

    JsonObject jsonRoot = getEntityArray(jsonArray, entityName);
    if (jsonRoot == null) {
      System.out.println("Entity " + entityName + " not found.");
      return;
    }

    JsonArray entityArray = jsonRoot.getAsJsonArray(entityName);
    for (JsonElement element : entityArray) {
      JsonObject entity = element.getAsJsonObject();
      if (entity.has("id") && entity.get("id").getAsInt() == entityId) {
        entityArray.remove(element);
        break;
      }
    }

    writeToFile(jsonArray, filePath);
  }

  public static void deleteEntityElement(
    String filePath,
    String entityName,
    int position
  ) {
    JsonArray jsonArray = readFromFile(filePath);

    if (jsonArray.isJsonArray() && jsonArray.size() > 0) {
      JsonElement firstElement = jsonArray.get(position);

      if (firstElement.isJsonObject()) {
        JsonObject jsonRoot = firstElement.getAsJsonObject();

        if (jsonRoot.has(entityName)) {
          JsonElement entityElement = jsonRoot.get(entityName);
          if (entityElement.isJsonObject()) {
            JsonObject entityObject = entityElement.getAsJsonObject();
            entityObject.entrySet().removeIf(entry -> true); // Remove all properties
          } else {
            System.out.println(
              "Entity " + entityName + " is not a JsonObject."
            );
          }
        } else {
          System.out.println("Entity " + entityName + " not found.");
        }

        writeToFileElement(jsonArray, filePath);
      } else {
        System.out.println("Unsupported JSON structure.");
      }
    } else {
      System.out.println("JSON array is empty.");
    }
  }

  private static JsonArray readFromFile(String filePath) {
    try (FileReader fileReader = new FileReader(filePath)) {
      return JsonParser.parseReader(fileReader).getAsJsonArray();
    } catch (IOException e) {
      e.printStackTrace();
      return new JsonArray();
    }
  }

  private static void writeToFile(JsonArray jsonArray, String filePath) {
    try (FileWriter fileWriter = new FileWriter(filePath)) {
      gson.toJson(jsonArray, fileWriter);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void writeToFileElement(
    JsonElement jsonElement,
    String filePath
  ) {
    try (FileWriter fileWriter = new FileWriter(filePath)) {
      gson.toJson(jsonElement, fileWriter);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static JsonObject getOrCreateEntityArray(
    JsonArray jsonArray,
    String entityName
  ) {
    for (JsonElement jsonElement : jsonArray) {
      JsonObject jsonObject = jsonElement.getAsJsonObject();
      if (jsonObject.has(entityName)) {
        return jsonObject;
      }
    }

    JsonObject newEntityArray = new JsonObject();
    newEntityArray.add(entityName, new JsonArray());
    jsonArray.add(newEntityArray);
    return newEntityArray;
  }

  private static JsonObject getEntityArray(
    JsonArray jsonArray,
    String entityName
  ) {
    for (JsonElement jsonElement : jsonArray) {
      JsonObject jsonObject = jsonElement.getAsJsonObject();
      if (jsonObject.has(entityName)) {
        return jsonObject;
      }
    }
    return null;
  }

  public static void main(String[] args) {
    JsonUtils.printJson();
  }
}
