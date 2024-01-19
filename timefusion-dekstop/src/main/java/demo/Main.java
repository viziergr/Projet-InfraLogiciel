package demo;

import com.jibbow.fastis.*;
import com.jibbow.fastis.util.TimeInterval;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Jibbow on 8/11/17.
 */
public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    WeekView root1 = new WeekView(LocalDate.now(), new Calendar());
    Stage stage = new Stage();
    stage.setScene(new Scene(root1));
    stage.setTitle("Fastis");
    stage.setMinWidth(100);
    stage.setMinHeight(100);
    stage.show();
    // testLayout(root1);
    // testCalendar(root1);
  }

  private void testLayout(CalendarView calendarView) {
    Appointment a1 = new Appointment(
      new TimeInterval(LocalDateTime.now(), LocalDateTime.now().plusHours(2)),
      "app1"
    );
    Appointment a2 = new Appointment(
      new TimeInterval(
        LocalDateTime.now().plusHours(1),
        LocalDateTime.now().plusHours(3)
      ),
      "app2"
    );
    Appointment a3 = new Appointment(
      new TimeInterval(
        LocalDateTime.now().plusHours(1),
        LocalDateTime.now().plusHours(4)
      ),
      "app3"
    );
    Appointment a4 = new Appointment(
      new TimeInterval(
        LocalDateTime.now().plusHours(2),
        LocalDateTime.now().plusHours(3)
      ),
      "app4"
    );
    Appointment a5 = new Appointment(
      new TimeInterval(
        LocalDateTime.now().plusHours(3),
        LocalDateTime.now().plusHours(4)
      ),
      "app5"
    );
    Appointment a6 = new Appointment(
      new TimeInterval(
        LocalDateTime.now().plusHours(3),
        LocalDateTime.now().plusHours(5)
      ),
      "app6"
    );

    calendarView.getCalendars().clear();
    calendarView.getCalendars().add(new Calendar(a1, a2, a3, a4, a5, a6));
  }

  private void testCalendar(CalendarView calendarView) {
    Queue<Runnable> tasks = new LinkedList<>();

    Appointment app1 = new Appointment(
      new TimeInterval(LocalDateTime.now(), LocalDateTime.now().plusHours(3)),
      "Appointment1"
    );
    Appointment app2 = new Appointment(
      new TimeInterval(
        LocalDateTime.now().plusHours(4),
        LocalDateTime.now().plusHours(5)
      ),
      "Appointment2"
    );
    Appointment app3 = new Appointment(
      new TimeInterval(
        LocalDateTime.now().plusHours(5),
        LocalDateTime.now().plusHours(6)
      ),
      "Appointment3"
    );
    Appointment app_overlapping1 = new Appointment(
      new TimeInterval(
        LocalDateTime.now().plusHours(1),
        LocalDateTime.now().plusHours(3)
      ),
      "AppointmentO1"
    );
    Appointment app_overlapping2 = new Appointment(
      new TimeInterval(
        LocalDateTime.now().plusHours(2),
        LocalDateTime.now().plusHours(4)
      ),
      "AppointmentO2"
    );
    Appointment app_allday1 = new Appointment(LocalDate.now(), "Allday1");
    Appointment app_allday2 = new Appointment(LocalDate.now(), "Allday2");
    Calendar cal1 = new Calendar(app1, app2);
    Calendar cal2 = new Calendar();

    tasks.add(() -> {
      System.out.println("clearing all calendars");
      calendarView.getCalendars().clear();
    });

    tasks.add(() -> {
      System.out.println("adding a new calendar with two appointments");
      calendarView.getCalendars().add(cal1);
    });

    tasks.add(() -> {
      System.out.println("adding a third appointment to this calendar");
      cal1.add(app3);
    });

    tasks.add(() -> {
      System.out.println("adding an overlapping appointment");
      cal1.add(app_overlapping1);
      cal1.add(app_overlapping2);
    });

    tasks.add(() -> {
      System.out.println("removing the first appointment");
      cal1.remove(app1);
    });

    tasks.add(() -> {
      System.out.println("adding a new empty calendar");
      calendarView.getCalendars().add(cal2);
    });

    tasks.add(() -> {
      System.out.println(
        "adding the first appointment again to the second calendar"
      );
      cal2.add(app1);
    });

    tasks.add(() -> {
      System.out.println(
        "adding the second appointment also to the second calendar"
      );
      cal2.add(app2);
    });

    tasks.add(() -> {
      System.out.println("moving the second appointment 4 hours into the past");
      app2
        .intervalProperty()
        .set(
          new TimeInterval(
            LocalDateTime.now().minusHours(4),
            LocalDateTime.now().minusHours(3)
          )
        );
    });

    tasks.add(() -> {
      System.out.println("moving the first appointment to the next day");
      app1
        .intervalProperty()
        .set(
          new TimeInterval(
            LocalDateTime.now().plusDays(1),
            LocalDateTime.now().plusHours(2).plusDays(1)
          )
        );
    });

    tasks.add(() -> {
      System.out.println("Adding all-day appointment");
      cal1.add(app_allday1);
    });

    tasks.add(() -> {
      System.out.println("Adding another all-day appointment");
      cal1.add(app_allday2);
    });

    Timeline indicatorupdate = new Timeline(
      new KeyFrame(
        javafx.util.Duration.seconds(5),
        actionEvent -> tasks.poll().run()
      )
    );
    indicatorupdate.setCycleCount(tasks.size());
    indicatorupdate.play();
  }
}
