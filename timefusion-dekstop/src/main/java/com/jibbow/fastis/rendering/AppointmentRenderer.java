package com.jibbow.fastis.rendering;

import com.jibbow.fastis.Appointment;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

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
      p.getStyleClass().add("appointment-offline"); //appointment

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
    System.out.println("Clicked on appointment: " + appointment.toString());
    // Add your custom logic here
  }
}
