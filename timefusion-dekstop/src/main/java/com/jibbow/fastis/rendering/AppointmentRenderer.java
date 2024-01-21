package com.jibbow.fastis.rendering;

import com.jibbow.fastis.Appointment;
import com.timefusion.JSON.Entities.EventsEntity;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * A class for creating the nodes to an appointment.
 * This class is used by {@link FlexAppointmentFactory} and {@link StackingAppointmentFactory}.
 */
public class AppointmentRenderer {

  public static Region createAppointment(Appointment appointment) {
    if (appointment.isFullDayProperty().get()) {
      Pane p = new Pane();
      p
        .getStylesheets()
        .add(
          AppointmentRenderer.class.getResource(
              "/com/jibbow/fastis/resources/css/Appointment.css"
            )
            .toString()
        );
      p.getStyleClass().add("appointment-fullday");
      return p;
    } else {
      BorderPane p = new BorderPane();
      p
        .getStylesheets()
        .add(
          AppointmentRenderer.class.getResource(
              "/com/jibbow/fastis/resources/css/Appointment.css"
            )
            .toExternalForm()
        );
      if (appointment.isOffline()) {
        p.getStyleClass().add("appointment-offline"); //appointment
      } else if (appointment.isInvited()) {
        p.getStyleClass().add("appointment-invited");
      } else if (appointment.isPrivate()) {
        p.getStyleClass().add("appointment-private");
      } else {
        p.getStyleClass().add("appointment");
      }
      VBox content = new VBox();

      Label starttime = new Label(
        DateTimeFormatter
          .ofLocalizedTime(FormatStyle.SHORT)
          .format(appointment.startTimeProperty())
      );
      starttime.getStyleClass().add("lblStartTime");
      content.getChildren().add(starttime);
      Label title = new Label(appointment.titleProperty().get());
      content.getChildren().add(title);

      Pane leftBar = new Pane();
      leftBar.getStyleClass().add("leftbar");
      leftBar.setPrefWidth(5.0);

      p.setOnMouseClicked(event -> handleAppointmentClick(appointment));

      p.setLeft(leftBar);
      p.setCenter(content);
      p.setMargin(content, new Insets(0.0, 5.0, 0.0, 5.0));
      return p;
    }
  }

  private static void handleAppointmentClick(Appointment appointment) {
    Stage detailsStage = new Stage();
    detailsStage.initModality(Modality.APPLICATION_MODAL);
    detailsStage.setTitle("Appointment Details");

    Label titleLabel = new Label("Title: " + appointment.titleProperty().get());
    Label startTimeLabel = new Label(
      "Start Time: " +
      DateTimeFormatter
        .ofLocalizedTime(FormatStyle.SHORT)
        .format(appointment.startTimeProperty())
    );
    Label endTimeLabel = new Label(
      "End Time: " +
      DateTimeFormatter
        .ofLocalizedTime(FormatStyle.SHORT)
        .format(appointment.endTimeProperty())
    );
    Label descriptionLabel = new Label(
      "Description: " + appointment.getEventEntity().getDescription()
    );
    Label locationLabel = new Label(
      "Location: " + appointment.getEventEntity().getLocation()
    );

    VBox detailsVBox = new VBox(
      titleLabel,
      startTimeLabel,
      endTimeLabel,
      descriptionLabel,
      locationLabel
    );
    detailsVBox.setAlignment(Pos.CENTER);
    detailsVBox.setSpacing(10);
    detailsVBox.setPadding(new Insets(10));

    Button deleteButton = new Button("Delete");
    deleteButton.setOnAction(event -> {
      EventsEntity.deleteEventEntity(appointment.getEventEntity().getId());
      System.out.println("Deleting appointment: " + appointment.toString());
      detailsStage.close();
    });

    detailsVBox.getChildren().add(deleteButton);

    Scene detailsScene = new Scene(detailsVBox, 400, 250);
    detailsStage.setScene(detailsScene);

    detailsStage.show();
  }
}
