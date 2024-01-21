package com.jibbow.fastis;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.GridPane;

/**
 * Created by Jibbow on 8/13/17.
 */
public abstract class CalendarView extends GridPane {

  public static ObservableList<Calendar> calendars = FXCollections.observableArrayList();
  ObjectProperty<LocalDate> dateProperty;

  public static ObservableList<Calendar> getCalendars() {
    return calendars;
  }

  public ObjectProperty<LocalDate> getDate() {
    return dateProperty;
  }
}
