package com.timefusion.jfxcalendar.model;

import com.timefusion.jfxcalendar.views.JFXCalendar;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    // Event manager which contains all the events
    CalendarEventManager eventManager = new CalendarEventManager();

    // Calendar view
    JFXCalendar calendar = new JFXCalendar(eventManager);

    // Show the JavaFX stage
    primaryStage.setScene(calendar.getScene());
    primaryStage.show();
  }
}
