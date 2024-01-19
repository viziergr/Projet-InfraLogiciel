package com.timefusion.calendar.view;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class WeeklyView extends GridPane {

  private LocalDate startDate;

  public WeeklyView(LocalDate startDate) {
    this.startDate = startDate;
    initializeUI();
    this.setGridLinesVisible(true);
  }

  private void initializeUI() {
    createDayHeaders();

    populateEvents();

    setColumnConstraints();

    setCellAlignments();
  }

  private void createDayHeaders() {
    for (int i = 0; i < DayOfWeek.values().length; i++) {
      DayOfWeek dayOfWeek = DayOfWeek.from(startDate.plusDays(i));
      LocalDate date = startDate.plusDays(i);
      String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM d"));
      Label dayLabel = new Label(
        dayOfWeek.toString().substring(0, 3) + " " + formattedDate
      );
      dayLabel.setFont(Font.font(14));
      add(dayLabel, i, 0);
    }
  }

  private void populateEvents() {
    // Placeholder method for populating events in the grid
    // You can add event labels or other components based on your design
    // You might want to fetch events from your data model and display them here
    // For simplicity, let's add a label for each day as a placeholder

    for (int i = 0; i < DayOfWeek.values().length; i++) {
      Label eventLabel = new Label("Event " + (i + 1));
      add(eventLabel, i, 1);
    }
  }

  private void setCellAlignments() {
    for (int i = 0; i < getChildren().size(); i++) {
      if (getChildren().get(i) instanceof Label) {
        Label label = (Label) getChildren().get(i);
        System.out.println(label.getText());
        label.setAlignment(Pos.CENTER);
      }
    }
  }

  private void setColumnConstraints() {
    for (int i = 0; i < DayOfWeek.values().length; i++) {
      ColumnConstraints column = new ColumnConstraints();
      column.setPercentWidth(100.0 / DayOfWeek.values().length);
      getColumnConstraints().add(column);
    }
  }
}
