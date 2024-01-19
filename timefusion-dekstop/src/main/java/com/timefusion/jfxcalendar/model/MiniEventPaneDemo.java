package com.timefusion.jfxcalendar.model;

import com.timefusion.jfxcalendar.model.CalendarEvent;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MiniEventPaneDemo extends Application {

  @Override
  public void start(Stage primaryStage) {
    CalendarEvent sampleEvent = new CalendarEvent(
      "Sample Event",
      CalendarEvent.STANDARD
    );
    MiniEventPane miniEventPane = new MiniEventPane(sampleEvent);

    Scene scene = new Scene(miniEventPane, 300, 100);
    primaryStage.setScene(scene);
    primaryStage.setTitle("MiniEventPane Demo");
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
