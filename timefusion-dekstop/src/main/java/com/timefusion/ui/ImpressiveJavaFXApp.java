package com.timefusion.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ImpressiveJavaFXApp extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Impressive JavaFX App");

    GridPane grid = new GridPane();
    grid.setPadding(new Insets(20, 20, 20, 20));
    grid.setVgap(10);
    grid.setHgap(10);

    Label nameLabel = new Label("Enter your name:");
    TextField nameField = new TextField();
    Button greetButton = new Button("Greet");

    grid.add(nameLabel, 0, 0);
    grid.add(nameField, 1, 0);
    grid.add(greetButton, 1, 1);

    Label greetingLabel = new Label();

    greetButton.setOnAction(e -> {
      String name = nameField.getText();
      if (!name.isEmpty()) {
        greetingLabel.setText("Hello, " + name + "!");
      } else {
        greetingLabel.setText("Please enter your name.");
      }
    });

    grid.add(greetingLabel, 1, 2);

    Scene scene = new Scene(grid, 300, 200);
    primaryStage.setScene(scene);

    primaryStage.show();
  }
}
