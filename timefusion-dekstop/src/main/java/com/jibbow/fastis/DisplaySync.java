package com.jibbow.fastis;

import com.timefusion.JSON.Entities.EventNature;
import com.timefusion.JSON.Entities.EventsEntity;
import com.timefusion.sync.SyncUtil;
import demo.Main;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.collections.ObservableList;

public class DisplaySync {

  public static List<Integer> getNoneDisplayedEventsId() {
    List<Integer> allEventsId = SyncUtil.getLocalEventsIds();
    ObservableList<Calendar> calendar = CalendarView.getCalendars();
    List<Appointment> displayedEvents = calendar.get(0).getAppointments();
    List<Integer> displayedEventsId = new ArrayList<>();
    if (displayedEvents.size() > 0) {
      for (Appointment event : displayedEvents) {
        int eventId = event.getEventEntity().getId();
        displayedEventsId.add(eventId);
      }
    }
    if (displayedEventsId.size() > 0) {
      allEventsId.removeAll(displayedEventsId);
    }
    return allEventsId;
  }

  private static List<Integer> getEventsToDisplayIds() {
    List<Integer> allEventsId = new ArrayList<>();
    List<Integer> displaybleEventsId = new ArrayList<>();
    allEventsId = SyncUtil.getLocalEventsIds();
    if (allEventsId.size() > 0) {
      for (int eventId : allEventsId) {
        EventsEntity event = EventsEntity.getEventEntityById(eventId);
        if (
          event.getNature() == EventNature.ADDED ||
          event.getNature() == EventNature.INVITED ||
          event.getNature() == EventNature.UNCHANGED
        ) {
          displaybleEventsId.add(eventId);
        }
      }
    }
    return displaybleEventsId;
  }

  private static List<Integer> getEventsToNotDisplayIds() {
    List<Integer> allEventsId = SyncUtil.getLocalEventsIds();
    List<Integer> notDisplaybleEventsId = new ArrayList<>();
    List<Integer> displaybleEventsId = getEventsToDisplayIds();

    if (displaybleEventsId.size() > 0) {
      for (int eventId : allEventsId) {
        if (!displaybleEventsId.contains(eventId)) {
          notDisplaybleEventsId.add(eventId);
        }
      }
    }

    return notDisplaybleEventsId;
  }

  private static void displayedMissingAppointments() {
    List<Integer> noneDisplayedEventsId = getNoneDisplayedEventsId();
    List<Integer> eventsToDisplayIds = getEventsToDisplayIds();
    noneDisplayedEventsId.retainAll(eventsToDisplayIds);
    if (noneDisplayedEventsId.size() > 0) {
      for (int eventId : noneDisplayedEventsId) {
        EventsEntity event = EventsEntity.getEventEntityById(eventId);
        Appointment appointment = new Appointment(event);
        CalendarView.getCalendars().get(0).add(appointment);
      }
    }
    Main.getWeekView().update();
  }

  private static void removeElementsNotNeededToBeDisplayed() {
    List<Integer> noneDisplayedEventsId = getNoneDisplayedEventsId();
    List<Integer> eventsToNotDisplayIds = getEventsToNotDisplayIds();
    noneDisplayedEventsId.retainAll(eventsToNotDisplayIds);
    if (noneDisplayedEventsId.size() > 0) {
      for (int eventId : noneDisplayedEventsId) {
        EventsEntity event = EventsEntity.getEventEntityById(eventId);
        Appointment appointment = new Appointment(event);
        CalendarView.getCalendars().get(0).remove(appointment);
      }
    }
    Main.getWeekView().update();
  }

  //Evenements affichés qui ne le devraient pas
  private static void removeAmbiguousAppointments() {
    List<Integer> allEventsId = SyncUtil.getLocalEventsIds();
    ObservableList<Calendar> calendar = CalendarView.getCalendars();
    List<Appointment> displayedEvents = calendar.get(0).getAppointments();
    List<Integer> eventsToNotDisplayIds = getEventsToNotDisplayIds();
    if (displayedEvents.size() > 0) {
      for (Appointment event : displayedEvents) {
        int eventId = event.getEventEntity().getId();
        if (
          eventsToNotDisplayIds.contains(eventId) ||
          !allEventsId.contains(eventId)
        ) {
          CalendarView.getCalendars().get(0).remove(event);
        }
      }
    }
  }

  public static void synchronizeDisplay() {
    Platform.runLater(() -> {
      displayedMissingAppointments();
      removeElementsNotNeededToBeDisplayed();
      removeAmbiguousAppointments();
      Main.getWeekView().update();
    });
  }
}
