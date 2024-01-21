package demo;

import com.jibbow.fastis.*;
import com.jibbow.fastis.util.TimeInterval;
import com.timefusion.JSON.Entities.EventNature;
import com.timefusion.JSON.Entities.EventsEntity;
import com.timefusion.JSON.Entities.ParticipantsEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

/**
 * Created by Jibbow on 8/11/17.
 */
public class Main extends Application {

  private static WeekView weekView;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws InterruptedException {
    weekView = new WeekView(LocalDate.now(), new Calendar());
    Image customIcon = new Image(
      getClass().getResourceAsStream("/com/jibbow/fastis/resources/Logo.png")
    );
    Stage stage = new Stage();
    stage.getIcons().add(customIcon);
    stage.setScene(new Scene(weekView));
    stage.setTitle("TimeFusion 1.0");
    stage.setMinWidth(100);
    stage.setMinHeight(100);
    stage.show();
    System.out.println("Hello World");
    // root1.update();
    // Appointment app1 = new Appointment(
    //   new TimeInterval(LocalDateTime.now(), LocalDateTime.now().plusHours(3)),
    //   "Appointment1"
    // );
    // Appointment app2 = new Appointment(
    //   new TimeInterval(
    //     LocalDateTime.now().plusHours(4),
    //     LocalDateTime.now().plusHours(5)
    //   ),
    //   "Appointment2"
    // );
    // Appointment app3 = new Appointment(
    //   new TimeInterval(
    //     LocalDateTime.now().plusHours(1),
    //     LocalDateTime.now().plusHours(2)
    //   ),
    //   "Appointment3"
    // );
    //   root1.getCalendars().clear();
    //   Calendar cal1 = new Calendar(app1, app2, app3);
    //   root1.getCalendars().add(cal1);

    //   // Add event handler for the "space" key
    //   root1.setOnKeyReleased(event -> {
    //     if (event.getCode() == KeyCode.T) {
    //       // Delete the first appointment
    //       System.out.println("Deleting the first appointment");
    //       cal1.remove(app1);
    //     }
    //   });
  }

  public static WeekView getWeekView() {
    return weekView;
  }
}
