package com.timefusion.jfxcalendar.sync;

import com.google.gson.JsonObject;
import com.timefusion.dao.EventParticipantDao;
import com.timefusion.jfxcalendar.JSON.Entities.EventsEntity;
import com.timefusion.jfxcalendar.JSON.Entities.UserEntity;
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

  public static List<Integer> getDBEventsIds(DatabaseUtil databaseUtil) {
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

  public static List<Integer> getDBIdsNotInLocalStorage(
    DatabaseUtil databaseUtil
  ) {
    List<Integer> DBEventIds = getDBEventsIds(databaseUtil);
    DBEventIds.removeAll(getLocalOnlineEventsIds());
    return DBEventIds;
  }

  public static List<Integer> getLocalOnlineIcdsNotInDB(
    DatabaseUtil databaseUtil
  ) {
    List<Integer> localOnlineIds = getLocalOnlineEventsIds();
    localOnlineIds.removeAll(getDBEventsIds(databaseUtil));
    return localOnlineIds;
  }

  public static List<Integer> getLocalOfflineIdsNotInDB(
    DatabaseUtil databaseUtil
  ) {
    List<Integer> localOfflineIds = getLocalOfflineEventsIds();
    localOfflineIds.removeAll(getDBEventsIds(databaseUtil));
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

  public static List<Integer> getDBInvitedEvents(DatabaseUtil databaseUtil) {
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

  public static List<Integer> getDBInvitedEventsNotInLocal(
    DatabaseUtil databaseUtil
  ) {
    List<Integer> DBEventIds = getDBInvitedEvents(databaseUtil);
    DBEventIds.removeAll(getLocalOnlineEventsIds());
    return DBEventIds;
  }

  public static List<Integer> getLocalOnlineIdsNotInDB(
    DatabaseUtil databaseUtil
  ) {
    List<Integer> localOnlineIds = getLocalOnlineEventsIds();
    localOnlineIds.removeAll(getDBEventsIds(databaseUtil));
    return localOnlineIds;
  }

  public static void main(String[] args) {
    try {
      DatabaseUtil databaseUtil = new DatabaseUtil();
      System.out.println(getLocalOnlineIdsNotInDB(databaseUtil).toString());
    } catch (Exception e) {}
  }
}
