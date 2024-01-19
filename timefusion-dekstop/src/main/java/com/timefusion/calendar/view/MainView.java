package com.timefusion.calendar.view;

import java.time.LocalDate;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class MainView extends BorderPane {

  public MainView() {
    initializeUI();
  }

  private void initializeUI() {
    HBox topSection = createTopSection();
    setTop(topSection);

    WeeklyView weeklyView = new WeeklyView(LocalDate.now());
    setCenter(weeklyView);
  }

  private HBox createTopSection() {
    HBox topSection = new HBox();
    topSection.setAlignment(Pos.CENTER);
    topSection.setSpacing(10);

    Label titleLabel = new Label("Your Calendar App");
    topSection.getChildren().add(titleLabel);

    return topSection;
  }
}
