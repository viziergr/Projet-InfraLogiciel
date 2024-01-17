package com.timefusion.jfxcalendar.sync;

import com.google.gson.JsonObject;
import com.timefusion.dao.EventDao;
import com.timefusion.dao.EventParticipantDao;
import com.timefusion.dao.UserDao;
import com.timefusion.jfxcalendar.JSON.Entities.EventNature;
import com.timefusion.jfxcalendar.JSON.Entities.EventsEntity;
import com.timefusion.jfxcalendar.JSON.Entities.InformationEntity;
import com.timefusion.jfxcalendar.JSON.Entities.UserEntity;
import com.timefusion.model.Event;
import com.timefusion.model.EventParticipant;
import com.timefusion.model.User;
import com.timefusion.util.DatabaseUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DBToLocalStorageSync {

  public static int getLocalUserId() {
    if (UserEntity.isJsonUserEntityEmpty()) {
      return 0;
    }
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
        "SELECT id FROM event WHERE creator_id = " + getLocalUserId() + ";";
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
    if (DBIdsNotInLocalStorage.isEmpty() || DBIdsNotInLocalStorage == null) {
      return null;
    }
    String query =
      "SELECT * FROM " +
      EventDao.TABLE_NAME +
      " WHERE id IN (" +
      DBIdsNotInLocalStorage.toString().replace("[", "").replace("]", "") +
      ")";
    try {
      List<Map<String, Object>> mapEvents = databaseUtil.executeQuery(query);
      if (mapEvents.isEmpty() || mapEvents == null) {
        return null;
      }
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

  public static List<EventParticipant> getDBEventParticipantsFromEventID(
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
    List<EventParticipant> DBEventParticipants = getDBEventParticipantsFromEventID(
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

  public static List<Event> addParticipantEventsCreatedByOthersToLocal(
    DatabaseUtil databaseUtil
  ) {
    /*
     * 1: Get the ids of the events where the local user is a participant and
     * get the information of the user that created this event
     * 2: Get the retrieved events content required to create event object
     * 3: Create a participant with the creator and add it to the list of participants without the id of the local user
     */

    return null;
  }

  /**
   * Adds unimplemented database events to the local storage.
   * Retrieves unimplemented database events using the provided DatabaseUtil object,
   * and for each event, retrieves the participants and adds the event to the local storage.
   *
   * @param databaseUtil The DatabaseUtil object used to retrieve unimplemented database events.
   */
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
    List<Event> participantEvents = getParticipantEvents(databaseUtil);
    for (Event event : participantEvents) {
      EventsEntity eventsEntity = new EventsEntity(
        event,
        EventNature.UNCHANGED,
        getEventParticipants(databaseUtil, event.getId())
      );
      eventsEntity.addEventEntity();
    }
  }

  /**
   * Adds or updates the information entity based on the specified flags.
   *
   * @param updateLastUpdated Flag indicating whether to update the last updated timestamp.
   * @param updateLastSynced Flag indicating whether to update the last synced timestamp.
   */
  public static void addOrUpdateInformationEntity(
    boolean updateLastUpdated,
    boolean updateLastSynced
  ) {
    InformationEntity informationEntity = InformationEntity.getInformationEntity();
    if (!updateLastSynced && !updateLastUpdated) {
      return;
    }
    if (updateLastUpdated) {
      informationEntity.setLastUpdatedNow();
    }

    if (updateLastSynced) {
      informationEntity.setLastSyncedNow();
    }

    informationEntity.updateInformationEntity();
  }

  /**
   * Adds or updates a user entity in the database.
   *
   * @param user The user object to be added or updated.
   */
  public static void addOrUpdateUserEntity(User user) {
    UserEntity userEntity = UserEntity.userToUserEntity(user);
    userEntity.updateUserEntity();
  }

  public static List<Event> getParticipantEvents(DatabaseUtil databaseUtil) {
    List<Map<String, Object>> mapTeams = new ArrayList<>();
    try {
      String query =
        "SELECT * FROM " +
        EventDao.TABLE_NAME +
        " WHERE id IN (SELECT event_id FROM " +
        EventParticipantDao.TABLE_NAME +
        " WHERE participant_id = " +
        getLocalUserId() +
        ");";
      mapTeams = databaseUtil.executeQuery(query);
      if (mapTeams.isEmpty() || mapTeams == null) {
        return null;
      }
      List<Event> events = new ArrayList<>();
      for (Map<String, Object> mapTeam : mapTeams) {
        Event event = EventDao.mapResultSetToEvent(mapTeam);
        events.add(event);
      }
      return events;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static List<User> getEventParticipants(
    DatabaseUtil databaseUtil,
    int eventId
  ) {
    List<User> DBUsers = new ArrayList<>();

    try {
      String participantsQuery =
        "SELECT * FROM " +
        UserDao.TABLE_NAME +
        " WHERE id IN (SELECT participant_id FROM " +
        EventParticipantDao.TABLE_NAME +
        " WHERE event_id = " +
        eventId +
        " AND participant_id != " +
        getLocalUserId() +
        ")";
      List<Map<String, Object>> mapUsers = databaseUtil.executeQuery(
        participantsQuery
      );

      for (Map<String, Object> mapUser : mapUsers) {
        User user = UserDao.mapResultSetToUser(
          Collections.singletonList(mapUser)
        );
        DBUsers.add(user);
      }

      String creatorQuery =
        "SELECT * FROM " +
        UserDao.TABLE_NAME +
        " WHERE id IN (SELECT creator_id FROM " +
        EventDao.TABLE_NAME +
        " WHERE id = " +
        eventId +
        ")";
      List<Map<String, Object>> mapCreator = databaseUtil.executeQuery(
        creatorQuery
      );

      for (Map<String, Object> mapUser : mapCreator) {
        User user = UserDao.mapResultSetToUser(
          Collections.singletonList(mapUser)
        );
        DBUsers.add(user);
      }

      return DBUsers;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static void main(String[] args) {
    try {
      DatabaseUtil databaseUtil = new DatabaseUtil();
      addUnimplementedDBEventsToLocal(databaseUtil);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
