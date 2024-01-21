package com.jibbow.fastis.rendering;

import com.jibbow.fastis.Appointment;
import com.jibbow.fastis.DayChooser;
import com.jibbow.fastis.WeekView;
import com.jibbow.fastis.controlers.AddEventDialogController;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Created by Jibbow on 8/14/17.
 */
public class WeekViewRenderer {

  public Node createAllDayPane(List<Appointment> appointments) {
    // sort appointments by what?
    AnchorPane pane = new AnchorPane();
    int i = 0;
    for (Appointment a : appointments) {
      AnchorPane appPane = new AnchorPane();
      appPane.getStyleClass().add("allday-appointment-pane");
      appPane.setPrefHeight((appointments.size() - i) * 15);
      appPane.setMaxHeight(Region.USE_PREF_SIZE);
      appPane.setMinHeight(Region.USE_PREF_SIZE);
      AnchorPane.setLeftAnchor(appPane, 10.0 * i);
      AnchorPane.setBottomAnchor(appPane, 0.0);
      AnchorPane.setRightAnchor(appPane, 0.0);
      pane.getChildren().add(appPane);

      Label lblTitle = new Label(a.titleProperty().get());
      lblTitle.getStyleClass().add("allday-appointment-title");
      AnchorPane.setRightAnchor(lblTitle, 0.0);
      appPane.getChildren().add(lblTitle);

      i++;
    }
    return pane;
  }

  public Node createHeaderPane(WeekView calView) {
    final GridPane container = new GridPane();
    container.setAlignment(Pos.BOTTOM_LEFT);
    container.getStyleClass().add("headerpane");

    final Label lblWeekday = new Label(
      calView.getDate().get().format(DateTimeFormatter.ofPattern("EEE"))
    );
    lblWeekday.getStyleClass().add("label-weekday");

    // final Label lblDate = new Label(calView.getDate().get().toString());
    final Label lblDate = new Label();
    Image calendarImage = new Image(
      getClass()
        .getResourceAsStream("/com/jibbow/fastis/resources/CalendarIcon.png")
    );
    ImageView calendarImageView = new ImageView(calendarImage);
    calendarImageView.setFitHeight(30);
    calendarImageView.setFitWidth(30);
    lblDate.setGraphic(calendarImageView);
    lblDate.getStyleClass().add("label-date");
    final ContextMenu dayChooserMenu = new ContextMenu();
    final CustomMenuItem item = new CustomMenuItem(
      new DayChooser(calView.getDate())
    );
    dayChooserMenu.getStyleClass().add("day-chooser");
    item.setHideOnClick(false);
    dayChooserMenu.getItems().add(item);
    lblDate.setOnMouseClicked(event ->
      dayChooserMenu.show(
        lblDate,
        lblDate.localToScreen(0, 0).getX(),
        lblDate.localToScreen(0, 0).getY()
      )
    );

    final Button addAppointment = new Button("+");
    addAppointment.getStyleClass().add("header-button");
    addAppointment.setOnAction(event -> handleAddEventButtonClick());

    final Button left = new Button("<");
    left.getStyleClass().add("header-button");
    left.setOnAction(event ->
      calView.getDate().set(calView.getDate().get().minusDays(1))
    );
    final Button right = new Button(">");
    right.getStyleClass().add("header-button");
    right.setOnAction(event ->
      calView.getDate().set(calView.getDate().get().plusDays(1))
    );

    final Button logoutButton = new Button();
    Image logoutImage = new Image(
      getClass().getResourceAsStream("/com/jibbow/fastis/resources/logout.png")
    );
    ImageView logoutImageView = new ImageView(logoutImage);
    logoutImageView.setFitHeight(30);
    logoutImageView.setFitWidth(16);
    logoutButton.setGraphic(logoutImageView);
    logoutButton.getStyleClass().add("header-button");

    Image onlineImage = new Image(
      getClass().getResourceAsStream("/com/jibbow/fastis/resources/Online.png")
    );
    Image offlineImage = new Image(
      getClass().getResourceAsStream("/com/jibbow/fastis/resources/Offline.png")
    );
    final ToggleButton onlineOfflineButton = new ToggleButton(); //TODO: Add the logic to know if the application is first Online or Offline, if the application was last Offline -> Offline, if the application was last Online _> Online if None -> Offline
    onlineOfflineButton.setOnAction(event -> {
      if (onlineOfflineButton.isSelected()) {
        ImageView offlineImageView = new ImageView(offlineImage);
        offlineImageView.setFitHeight(30);
        offlineImageView.setFitWidth(100);
        onlineOfflineButton.setGraphic(offlineImageView);
        // TODO: Handle Offline state actions
      } else {
        ImageView onlineImageView = new ImageView(onlineImage);
        onlineImageView.setFitHeight(30);
        onlineImageView.setFitWidth(100);

        onlineOfflineButton.setGraphic(onlineImageView);
        //TODO:  Handle Online state actions
      }
    });
    onlineOfflineButton.getStyleClass().add("header-button");

    final ColumnConstraints columnWeekday = new ColumnConstraints(
      125,
      125,
      125
    );
    columnWeekday.setHalignment(HPos.LEFT);
    final ColumnConstraints columnCenter = new ColumnConstraints(
      0,
      50,
      Double.POSITIVE_INFINITY,
      Priority.ALWAYS,
      HPos.CENTER,
      true
    );
    final ColumnConstraints columnSwitcher = new ColumnConstraints(60, 60, 60);
    columnSwitcher.setHalignment(HPos.RIGHT);
    container
      .getColumnConstraints()
      .addAll(columnWeekday, columnCenter, columnSwitcher);
    container.add(
      new HBox(lblWeekday, lblDate) {
        {
          setAlignment(Pos.BASELINE_LEFT);
        }
      },
      0,
      0
    );
    container.add(addAppointment, 1, 0);
    HBox rightBox1 = new HBox(left, right);
    HBox rightBox2 = new HBox(onlineOfflineButton, logoutButton);
    rightBox2.setSpacing(10);
    HBox rightBox = new HBox(rightBox1, rightBox2);
    // rightBox.getStyleClass().add(button-container);
    rightBox.setSpacing(10);
    container.add(rightBox, 2, 0);
    return container;
  }

  public Node createDayBackground(LocalDate date) {
    Pane p = new Pane();
    if (
      date.getDayOfWeek().equals(DayOfWeek.SUNDAY) ||
      date.getDayOfWeek().equals(DayOfWeek.SATURDAY)
    ) {
      p.getStyleClass().add("day-background-weekend");
    }
    if (date.equals(LocalDate.now())) {
      p.getStyleClass().add("day-background-today");
    }
    p.getStyleClass().add("day-background");
    return p;
  }

  public Node createSingleDayHeader(LocalDate date) {
    final Label lblWeekday = new Label(
      date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault())
    );
    lblWeekday.getStyleClass().add("header-weekday");
    final Label lblDate = new Label(
      DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(date)
    );
    lblDate.getStyleClass().add("header-date");
    VBox container = new VBox(lblDate, lblWeekday);
    container.getStyleClass().add("header-container");
    if (date.equals(LocalDate.now())) {
      container.getStyleClass().add("header-container-today");
    }
    container.setAlignment(Pos.TOP_CENTER);
    return container;
  }

  private void handleAddEventButtonClick() {
    try {
      // Load the FXML file for the AddEventDialog
      FXMLLoader loader = new FXMLLoader(
        getClass()
          .getResource("/com/jibbow/fastis/resources/fxml/AddEventDialog.fxml")
      );
      VBox dialog = loader.load();

      Scene scene = new Scene(dialog);
      Stage stage = new Stage();
      stage.setScene(scene);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}