package com.timefusion.jfxcalendar.sync;

import com.google.gson.JsonObject;
import com.timefusion.dao.EventDao;
import com.timefusion.dao.EventParticipantDao;
import com.timefusion.dao.UserDao;
import com.timefusion.exception.EventException;
import com.timefusion.jfxcalendar.JSON.Entities.EventNature;
import com.timefusion.jfxcalendar.JSON.Entities.EventsEntity;
import com.timefusion.jfxcalendar.JSON.Entities.ParticipantsEntity;
import com.timefusion.model.Event;
import com.timefusion.model.EventParticipant;
import com.timefusion.model.User;
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
      "SELECT * FROM " +
      EventDao.TABLE_NAME +
      " WHERE id IN (" +
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

  public static List<EventParticipant> getDBEventParticipants(
    DatabaseUtil databaseUtil,
    int eventId
  ) {
    String query =
      "SELECT * FROM " +
      EventParticipantDao.TABLE_NAME +
      " WHERE event_id = " +
      eventId +
      ";";
    try {
      List<Map<String, Object>> mapEventParticipants = databaseUtil.executeQuery(
        query
      );
      List<EventParticipant> DBEventParticipants = new ArrayList<>();
      for (Map<String, Object> mapEventParticipant : mapEventParticipants) {
        EventParticipant eventParticipant = EventParticipantDao.mapResultSetToEventParticipant(
          mapEventParticipant
        );
        DBEventParticipants.add(eventParticipant);
      }
      return DBEventParticipants;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static List<User> getDBParticipantsFromEventId(
    DatabaseUtil databaseUtil,
    int eventId
  ) {
    List<User> DBUsers = new ArrayList<>();
    List<EventParticipant> DBEventParticipants = getDBEventParticipants(
      databaseUtil,
      eventId
    );
    List<Integer> participantIds = new ArrayList<>();
    for (EventParticipant eventParticipant : DBEventParticipants) {
      participantIds.add(eventParticipant.getParticipantId());
    }
    if (participantIds.isEmpty()) {
      return DBUsers;
    }
    try {
      String query =
        "SELECT * FROM " +
        UserDao.TABLE_NAME +
        " WHERE id IN (" +
        participantIds.toString().replace("[", "").replace("]", "") +
        ")";
      List<Map<String, Object>> result = databaseUtil.executeQuery(query);

      if (!result.isEmpty()) {
        for (Map<String, Object> mapUser : result) {
          List<Map<String, Object>> listMapUser = new ArrayList<>();
          listMapUser.add(mapUser);
          User user = UserDao.mapResultSetToUser(listMapUser);
          DBUsers.add(user);
        }
      } else {
        System.out.println("No user found");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return DBUsers;
  }

  public static void addUnimplementedDBEventsToLocal(
    DatabaseUtil databaseUtil
  ) {
    List<Event> unimplementedDBEvents = getUnimplementedDBEvents(databaseUtil);
    for (Event event : unimplementedDBEvents) {
      // for each event retrieve the participants
      EventsEntity eventsEntity = new EventsEntity(
        event,
        EventNature.UNCHANGED,
        getDBParticipantsFromEventId(databaseUtil, event.getId())
      );
      eventsEntity.addEventEntity();
    }
  }

  public static void main(String[] args) {
    try {
      DatabaseUtil databaseUtil = new DatabaseUtil();
      // System.out.println(getUnimplementedDBEvents(databaseUtil).toString());
      addUnimplementedDBEventsToLocal(databaseUtil);
      System.out.println(
        getDBParticipantsFromEventId(databaseUtil, 2).toString()
      );
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
