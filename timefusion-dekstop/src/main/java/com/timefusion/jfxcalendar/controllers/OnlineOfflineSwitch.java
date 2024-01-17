package com.timefusion.jfxcalendar.controllers;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OnlineOfflineSwitch extends Application {

  public static boolean isOnline = false; // Initial state is offline

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Online/Offline Switch");

    ToggleButton switchButton = new ToggleButton();
    switchButton.getStyleClass().addAll("switch-button", "offline");
    switchButton.setSelected(!isOnline); // Set initial state
    switchButton.setOnAction(e -> handleSwitchButtonClick(switchButton));

    Label statusLabel = new Label("Offline");
    statusLabel.getStyleClass().add("status-label");

    VBox vBox = new VBox(createSwitchContainer(switchButton), statusLabel);
    vBox.setAlignment(Pos.CENTER);
    vBox.setSpacing(20);

    Scene scene = new Scene(vBox, 200, 150);
    scene
      .getStylesheets()
      .add(getClass().getResource("styles.css").toExternalForm());

    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private StackPane createSwitchContainer(ToggleButton switchButton) {
    StackPane switchContainer = new StackPane(switchButton);
    switchContainer.getStyleClass().add("switch-container");
    return switchContainer;
  }

  private void handleSwitchButtonClick(ToggleButton switchButton) {
    isOnline = switchButton.isSelected();
    switchButton.getStyleClass().removeAll("online", "offline");
    switchButton.getStyleClass().add(isOnline ? "online" : "offline");

    Label statusLabel = (Label) (
      (VBox) switchButton.getParent().getParent()
    ).getChildren()
      .get(1);
    statusLabel.setText(isOnline ? "Online" : "Offline");
    statusLabel.getStyleClass().removeAll("online", "offline");
    statusLabel.getStyleClass().add(isOnline ? "online" : "offline");
  }
}
