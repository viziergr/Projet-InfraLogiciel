package com.jibbow.fastis;

import com.jibbow.fastis.util.TimeInterval;
import com.timefusion.JSON.Entities.EventsEntity;
import java.time.temporal.Temporal;
import javafx.beans.property.*;

/**
 * Created by Jibbow on 8/11/17.
 */
public class Appointment {

  private ObjectProperty<TimeInterval> intervalProperty;
  private StringProperty titleProperty;
  private BooleanProperty isFullDayProperty = new SimpleBooleanProperty(false);
  private EventsEntity eventEntity;

  public Appointment(EventsEntity event) {
    this.intervalProperty =
      new SimpleObjectProperty<>(
        new TimeInterval(event.getStartTime(), event.getEndTime())
      );
    this.titleProperty = new SimpleStringProperty(event.getTitle());
    this.isFullDayProperty = new SimpleBooleanProperty(false);
    this.eventEntity = event;
  }

  // Rest of the code...

  public BooleanProperty isFullDayProperty() {
    return isFullDayProperty;
  }

  public StringProperty titleProperty() {
    return titleProperty;
  }

  public ObjectProperty<TimeInterval> intervalProperty() {
    return intervalProperty;
  }

  public Temporal startTimeProperty() {
    return intervalProperty.get().getStartDateTime();
  }

  public Temporal endTimeProperty() {
    return intervalProperty.get().getEndDateTime();
  }

  public EventsEntity getEventEntity() {
    return eventEntity;
  }

  public Appointment getAppointmentByEventsEntity(EventsEntity event) {
    if (this.eventEntity == event) {
      return this;
    }
    return null;
  }

  @Override
  public String toString() {
    return (
      "Appointment [intervalProperty=" +
      intervalProperty +
      ", titleProperty=" +
      titleProperty +
      ", isFullDayProperty=" +
      isFullDayProperty +
      ", event=" +
      eventEntity +
      "]"
    );
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Appointment) {
      Appointment other = (Appointment) obj;
      return (
        other.intervalProperty().get().equals(intervalProperty().get()) &&
        other.titleProperty().get().equals(titleProperty().get()) &&
        other.isFullDayProperty().get() == isFullDayProperty().get() &&
        other.getEventEntity().equals(eventEntity)
      );
    }
    return false;
  }
}
