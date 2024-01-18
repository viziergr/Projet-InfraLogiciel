package com.timefusion.sync;

import com.timefusion.JSON.Entities.EventNature;
import com.timefusion.JSON.Entities.EventsEntity;
import com.timefusion.dao.EventDao;
import com.timefusion.dao.EventParticipantDao;
import com.timefusion.dao.GenericDao;
import com.timefusion.model.Event;
import com.timefusion.util.DatabaseUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LocalToRemoteEventSync {

  public static void handleLocalDeletedEvents(EventDao eventDao) {
    //Deux cas, soit l'event est en ligne et il faut le supprimer de la base de données et du fichier json
    //Soit l'event est hors ligne et il faut le supprimer du fichier json
    //Si le local user n'est pas le creator alors DENY logic...
    // TODO : maybe doesn't work
    List<Integer> offlineDeletedEventsIds = SyncUtil.getOfflineDeletedEventsIds();
    if (offlineDeletedEventsIds.size() > 0) {
      for (int i = 0; i < offlineDeletedEventsIds.size(); i++) {
        int eventId = offlineDeletedEventsIds.get(i);
        if (eventId > 0) {
          try {
            int rowDeleted = eventDao.deleteEventRecord(
              EventsEntity.eventEntityToEvent(
                EventsEntity.getEventEntityById(eventId)
              )
            );
          } catch (SQLException e) {
            e.printStackTrace();
          }
        }
        EventsEntity.deleteEventEntity(eventId);
      }
    }
  }

  public static void handleOfflineAddedEvents(EventDao eventDao) {
    List<Integer> offlineAddedEventsIds = SyncUtil.getOfflineAddedEventsIds();
    if (offlineAddedEventsIds.size() > 0) {
      for (int eventId : offlineAddedEventsIds) {
        try {
          Event event = EventsEntity.eventEntityToEvent(
            EventsEntity.getEventEntityById(eventId)
          );
          event.setId(0);
          int rowInsertedId = eventDao.insertEventRecord(event);
          if (rowInsertedId > 0) {
            EventsEntity eventsEntity = EventsEntity.getEventEntityById(
              eventId
            );
            EventsEntity.deleteEventEntity(eventId);
            eventsEntity.setId(rowInsertedId);
            eventsEntity.setNature(EventNature.UNCHANGED);
            eventsEntity.addEventEntity();
          }
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static void handleOfflineDeniedEvents(
    EventParticipantDao eventParticipantDao
  ) {
    List<Integer> offlineDeniedEventsIds = SyncUtil.getLocalDeniedEventsIds();

    if (offlineDeniedEventsIds.size() > 0) {
      for (int eventId : offlineDeniedEventsIds) {
        {
          try {
            deniedEvent(
              eventParticipantDao,
              EventsEntity.eventEntityToEvent(
                EventsEntity.getEventEntityById(eventId)
              )
            );
          } catch (IllegalAccessError e) {
            e.printStackTrace();
          }
        }
      }
    }
  }

  private static void deniedEvent(
    EventParticipantDao eventParticipantDao,
    Event event
  ) {
    try {
      String query =
        "SELECT id FROM " +
        EventParticipantDao.TABLE_NAME +
        " WHERE event_id = " +
        event.getId() +
        " AND participant_id = " +
        SyncUtil.getLocalUserId() +
        ";";

      List<Map<String, Object>> mapId = eventParticipantDao
        .getDatabaseUtil()
        .executeQuery(query);
      if (mapId.size() > 0) {
        eventParticipantDao.deleteEventParticipantRecordByEventId(
          (Integer) mapId.get(0).get("id")
        );
        EventsEntity.deleteEventEntity(event.getId());
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static boolean isEventInvitation(int eventId) {
    // Ajouter un champ au json isInvited? Mettre cette fonction dans EventsEntity
    return false;
  }

  public static void synchronize(EventDao eventDao) {
    try {
      handleLocalDeletedEvents(eventDao);
      handleOfflineAddedEvents(eventDao);
      handleOfflineDeniedEvents(new EventParticipantDao());
    } catch (Exception e) {}
  }

  public static void main(String[] args) {
    try {
      synchronize(new EventDao());
    } catch (Exception e) {}
  }
}
