package main.java;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Test extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    // Create a label
    Label label = new Label("Hello, JavaFX!");

    // Create a scene with the label
    Scene scene = new Scene(label, 300, 200);

    // Set the scene to the stage
    primaryStage.setScene(scene);

    // Set the title of the stage
    primaryStage.setTitle("Simple JavaFX App");

    // Show the stage
    primaryStage.show();
  }
}
