package com.timefusion.calendar.Application;

import com.timefusion.calendar.view.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CalendarApp extends Application {

  @Override
  public void start(Stage primaryStage) {
    // Instantiate your main view
    MainView mainView = new MainView();

    // Set up the scene
    Scene scene = new Scene(mainView, 800, 600);

    // Set up the stage
    primaryStage.setTitle("Your Calendar App");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
