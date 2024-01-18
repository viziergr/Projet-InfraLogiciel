package com.timefusion.jfxcalendar.sync;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.timefusion.dao.EventParticipantDao;
import com.timefusion.jfxcalendar.JSON.Entities.EventNature;
import com.timefusion.jfxcalendar.JSON.Entities.EventsEntity;
import com.timefusion.jfxcalendar.JSON.Entities.UserEntity;
import com.timefusion.jfxcalendar.JSON.JsonUtils;
import com.timefusion.util.DatabaseUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SyncUtil {

  public static int getLocalUserId() {
    UserEntity userEntity = UserEntity.getuserEntityFromJson();
    return userEntity.getId();
  }

  public static List<Integer> getLocalOnlineEventsIds() {
    List<Integer> nonNegativeIds = new ArrayList<>();

    for (int i = 0; i < EventsEntity.getAllEventEntities().size(); i++) {
      JsonObject eventObject = EventsEntity
        .getAllEventEntities()
        .get(i)
        .getAsJsonObject();
      int eventId = eventObject.get("id").getAsInt();

      if (eventId >= 0) {
        nonNegativeIds.add(eventId);
      }
    }

    return nonNegativeIds;
  }

  public static List<Integer> getLocalOfflineEventsIds() {
    List<Integer> negativeIds = new ArrayList<>();

    for (int i = 0; i < EventsEntity.getAllEventEntities().size(); i++) {
      JsonObject eventObject = EventsEntity
        .getAllEventEntities()
        .get(i)
        .getAsJsonObject();
      int eventId = eventObject.get("id").getAsInt();

      if (eventId < 0) {
        negativeIds.add(eventId);
      }
    }

    return negativeIds;
  }

  public static List<Integer> getRemoteEventsIds(DatabaseUtil databaseUtil) {
    try {
      List<Integer> listIds = new ArrayList<>();
      String query =
        "SELECT event_id as id FROM " +
        EventParticipantDao.TABLE_NAME +
        " WHERE participant_id = " +
        getLocalUserId() +
        " UNION " +
        "SELECT id FROM event WHERE creator_id = " +
        getLocalUserId() +
        ";";
      List<Map<String, Object>> mapIds = databaseUtil.executeQuery(query);
      for (Map<String, Object> mapId : mapIds) {
        listIds.add((Integer) mapId.get("id"));
      }
      return listIds;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return new ArrayList<>();
  }

  public static List<Integer> getRemoteIdsNotInLocalStorage(
    DatabaseUtil databaseUtil
  ) {
    List<Integer> DBEventIds = getRemoteEventsIds(databaseUtil);
    DBEventIds.removeAll(getLocalOnlineEventsIds());
    return DBEventIds;
  }

  public static List<Integer> getLocalOnlineIcdsNotInRemote(
    DatabaseUtil databaseUtil
  ) {
    List<Integer> localOnlineIds = getLocalOnlineEventsIds();
    localOnlineIds.removeAll(getRemoteEventsIds(databaseUtil));
    return localOnlineIds;
  }

  public static List<Integer> getLocalOfflineIdsNotInRemote(
    DatabaseUtil databaseUtil
  ) {
    List<Integer> localOfflineIds = getLocalOfflineEventsIds();
    localOfflineIds.removeAll(getRemoteEventsIds(databaseUtil));
    return localOfflineIds;
  }

  public static List<Integer> getNormalEventsNotInLocal(
    DatabaseUtil databaseUtil
  ) {
    try {
      List<Integer> listIds = new ArrayList<>();
      String query =
        "SELECT id FROM event WHERE creator_id = " + getLocalUserId() + ";";
      List<Map<String, Object>> mapIds = databaseUtil.executeQuery(query);
      for (Map<String, Object> mapId : mapIds) {
        listIds.add((Integer) mapId.get("id"));
      }
      listIds.removeAll(getLocalOnlineEventsIds());
      return listIds;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return new ArrayList<>();
  }

  public static List<Integer> getRemoteInvitedEvents(
    DatabaseUtil databaseUtil
  ) {
    try {
      List<Integer> listIds = new ArrayList<>();
      String query =
        "SELECT event_id as id FROM " +
        EventParticipantDao.TABLE_NAME +
        " WHERE participant_id = " +
        getLocalUserId() +
        ";";
      List<Map<String, Object>> mapIds = databaseUtil.executeQuery(query);
      for (Map<String, Object> mapId : mapIds) {
        listIds.add((Integer) mapId.get("event_id"));
      }
      return listIds;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return new ArrayList<>();
  }

  public static List<Integer> getRemoteInvitedEventsNotInLocal(
    DatabaseUtil databaseUtil
  ) {
    List<Integer> DBEventIds = getRemoteInvitedEvents(databaseUtil);
    DBEventIds.removeAll(getLocalOnlineEventsIds());
    return DBEventIds;
  }

  public static List<Integer> getLocalOnlineIdsNotInRemote(
    DatabaseUtil databaseUtil
  ) {
    List<Integer> localOnlineIds = getLocalOnlineEventsIds();
    localOnlineIds.removeAll(getRemoteEventsIds(databaseUtil));
    return localOnlineIds;
  }

  public static List<EventsEntity> getOfflineEvents() {
    List<EventsEntity> offlineEvents = new ArrayList<>();
    List<Integer> negativeIds = SyncUtil.getLocalOfflineEventsIds();
    if (negativeIds.size() > 0) {
      for (int i = 0; i < negativeIds.size(); i++) {
        offlineEvents.add(EventsEntity.getEventEntityById(negativeIds.get(i)));
      }
    }
    return offlineEvents;
  }

  public static List<Integer> getOfflineDeletedEventsIds() {
    JsonElement eventsJsonElement = JsonUtils.readJsonPart(
      EventsEntity.EVENTS_ENTITY_NAME
    );
    List<Integer> deletedEvents = new ArrayList<>();

    if (eventsJsonElement.isJsonArray()) {
      JsonArray eventsJsonArray = eventsJsonElement.getAsJsonArray();
      for (JsonElement eventElement : eventsJsonArray) {
        JsonObject eventObject = eventElement.getAsJsonObject();
        if (
          eventObject
            .get("nature")
            .getAsString()
            .equals(EventNature.DELETED.toString())
        ) {
          deletedEvents.add(eventObject.get("id").getAsInt());
        }
      }
    }
    return deletedEvents;
  }

  public static List<Integer> getOfflineAddedEventsIds() {
    List<Integer> localOfflineIds = getLocalOfflineEventsIds();
    List<Integer> localOfflineAddedIds = new ArrayList<>();
    for (int i = 0; i < localOfflineIds.size(); i++) {
      if (
        EventsEntity
          .getEventEntityById(localOfflineIds.get(i))
          .getNature()
          .equals(EventNature.ADDED)
      ) {
        System.out.println("added");
        localOfflineAddedIds.add(localOfflineIds.get(i));
      }
    }
    return localOfflineAddedIds;
  }

  public static List<Integer> getLocalDeniedEventsIds() {
    List<Integer> localOnlineEventsIds = getLocalOnlineEventsIds();
    List<Integer> localDeniedEventsIds = new ArrayList<>();
    for (int i = 0; i < localOnlineEventsIds.size(); i++) {
      if (
        EventsEntity
          .getEventEntityById(localOnlineEventsIds.get(i))
          .getNature()
          .equals(EventNature.DENIED)
      ) {
        localDeniedEventsIds.add(localOnlineEventsIds.get(i));
      }
    }
    return localDeniedEventsIds;
  }

  public static void main(String[] args) {
    try {
      DatabaseUtil databaseUtil = new DatabaseUtil();
      System.out.println(getLocalDeniedEventsIds().toString());
    } catch (Exception e) {}
  }
}
