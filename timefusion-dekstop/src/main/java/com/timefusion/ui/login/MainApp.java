package com.timefusion.ui.login;

import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    // Load FXML file
    Parent root = FXMLLoader.load(
      getClass()
        .getResource("/com/timefusion/ui/login/resources/fxml/log-in.fxml")
    );

    Scene scene = new Scene(root);

    stage.setTitle("Login");
    stage.setScene(scene);
    stage.show();
  }

  /**
   * The main() method is ignored in correctly deployed JavaFX application.
   * main() serves only as fallback in case the application can not be
   * launched through deployment artifacts, e.g., in IDEs with limited FX
   * support. NetBeans ignores main().
   *
   * @param args the command line arguments
   * @throws SQLException
   */
  public static void main(String[] args) {
    launch(args);
  }
}
