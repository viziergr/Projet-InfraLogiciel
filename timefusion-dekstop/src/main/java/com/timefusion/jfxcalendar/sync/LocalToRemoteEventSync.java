package com.timefusion.jfxcalendar.sync;

import com.timefusion.dao.EventDao;
import com.timefusion.dao.GenericDao;
import com.timefusion.jfxcalendar.JSON.Entities.EventsEntity;
import com.timefusion.model.Event;
import com.timefusion.util.DatabaseUtil;
import java.sql.SQLException;
import java.util.List;

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
            eventsEntity.addEventEntity();
          }
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static void handleOfflineDeletedEvents(DatabaseUtil databaseUtil) {
    // #TODO
  }

  public static void main(String[] args) {
    try {
      EventDao eventDao = new EventDao();
      handleOfflineAddedEvents(eventDao);
    } catch (Exception e) {}
  }
}
