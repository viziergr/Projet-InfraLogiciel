package demo;

import com.jibbow.fastis.*;
import com.jibbow.fastis.util.TimeInterval;
import com.timefusion.JSON.Entities.EventNature;
import com.timefusion.JSON.Entities.EventsEntity;
import com.timefusion.JSON.Entities.ParticipantsEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javafx.application.Application;
import javafx.application.Platform;
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

    stage.setOnCloseRequest(event -> {
      // Perform cleanup actions here if needed
      Platform.exit();
      System.exit(0);
    });

    stage.show();
    System.out.println("Hello World");
  }

  public static WeekView getWeekView() {
    return weekView;
  }
}
