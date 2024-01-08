package com.timefusion.jfxcalendar.model;

import com.timefusion.jfxcalendar.views.JFXCalendar;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainApp extends Application {

  @Override
  public void start(Stage primaryStage) {
    // Event manager which contains all the events
    CalendarEventManager eventManager = new CalendarEventManager();

    // Calendar view
    JFXCalendar calendar = new JFXCalendar(eventManager);

    // Create a Pane to hold the calendar
    Pane rootPane = new Pane();
    rootPane.getChildren().add(calendar);

    // Set up the scene and stage
    Scene scene = new Scene(rootPane, 800, 600);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Your Calendar App");
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
