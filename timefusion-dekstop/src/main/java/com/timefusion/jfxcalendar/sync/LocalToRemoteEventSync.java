package com.timefusion.jfxcalendar.sync;

import com.timefusion.jfxcalendar.JSON.Entities.EventNature;
import com.timefusion.jfxcalendar.JSON.Entities.EventsEntity;
import com.timefusion.model.Event;
import java.util.ArrayList;
import java.util.List;

public class LocalToRemoteEventSync {

  public static List<Integer> getOfflineDeletedEvents() {
    //Retrieve both online and offline events that have been deleted
    List<Integer> deletedEvents = new ArrayList<>();

    for (int i = 0; i < EventsEntity.getAllEventEntities().size(); i++) {
      Event event = EventsEntity.getAllEventEntities().get(i);
      if (event.getNature().equals(EventNature.DELETED)) {
        deletedEvents.add(event.getId());
      }
    }

    return deletedEvents;
  }

  public static void main(String[] args) {
    List<EventsEntity> offlineEvents = SyncUtil.getOfflineEvents();
    for (int i = 0; i < offlineEvents.size(); i++) {
      System.out.println(
        offlineEvents.get(i).getNature().equals(EventNature.ADDED)
      );
    }
  }
}
