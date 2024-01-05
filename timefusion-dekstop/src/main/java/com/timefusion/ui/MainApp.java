package com.timefusion.ui;

import com.timefusion.util.DatabaseUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    // Print resource path to console
    System.out.println(
      getClass().getResource("/com/timefusion/fxml/log-in.fxml")
    );

    // Load FXML file
    Parent root = FXMLLoader.load(
      getClass().getResource("/com/timefusion/fxml/log-in.fxml")
    );

    Scene scene = new Scene(root);

    // Omitted stylesheet for no additional styling
    // scene.getStylesheets().add("/styles/Styles.css");

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
