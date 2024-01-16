package com.timefusion.jfxcalendar.sync;

import com.google.gson.JsonObject;
import com.timefusion.dao.EventDao;
import com.timefusion.jfxcalendar.JSON.Entities.EventsEntity;
import com.timefusion.model.Event;
import com.timefusion.util.DatabaseUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DBToLocalStorageSync {

  //   private List<EventsEntity> getUnimplementedEvents() {
  //       //Retrieve all the id of the online that are stored locally and
  //       //try to see which one are not implemented in the db

  //       return null;
  //   }

  //   private void createOrUpdateLocalEvents(List<EventsEntity> events) {

  //   }

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

  public static List<Integer> getDBEventsIds(DatabaseUtil databaseUtil) {
    try {
      List<Integer> listIds = new ArrayList<>();
      String query = "SELECT id FROM event";
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

  private static List<Integer> getDBIdsNotInLocalStorage(
    DatabaseUtil databaseUtil
  ) {
    List<Integer> DBEventIds = getDBEventsIds(databaseUtil);
    DBEventIds.removeAll(getLocalOnlineEventsIds());
    return DBEventIds;
  }

  private static List<Integer> getLocalOnlineIdsNotInDB(
    DatabaseUtil databaseUtil
  ) {
    List<Integer> localOnlineIds = getLocalOnlineEventsIds();
    localOnlineIds.removeAll(getDBEventsIds(databaseUtil));
    return localOnlineIds;
  }

  public static List<Event> getUnimplementedDBEvents(
    DatabaseUtil databaseUtil
  ) {
    List<Integer> DBIdsNotInLocalStorage = getDBIdsNotInLocalStorage(
      databaseUtil
    );
    String query =
      "SELECT * FROM event WHERE id IN (" +
      DBIdsNotInLocalStorage.toString().replace("[", "").replace("]", "") +
      ")";
    try {
      List<Map<String, Object>> mapEvents = databaseUtil.executeQuery(query);
      List<Event> unimplementedDBEvents = new ArrayList<>();
      for (Map<String, Object> mapEvent : mapEvents) {
        Event event = EventDao.mapResultSetToEvent(mapEvent);
        unimplementedDBEvents.add(event);
      }
      return unimplementedDBEvents;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static void main(String[] args) {
    try {
      DatabaseUtil databaseUtil = new DatabaseUtil();
      //   System.out.println(getDBIdsNotInLocalStorage(databaseUtil));
      //   System.out.println(getLocalOnlineIdsNotInDB(databaseUtil));
      System.out.println(getUnimplementedDBEvents(databaseUtil).toString());
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
