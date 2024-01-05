/**
 * Sample Skeleton for 'log-in.fxml' Controller Class
 */

package com.timefusion.ui;

import com.timefusion.dao.UserDao;
import com.timefusion.exception.AuthenticationException;
import com.timefusion.service.AuthService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LogInController {

  @FXML // ResourceBundle that was given to the FXMLLoader
  private ResourceBundle resources;

  @FXML // URL location of the FXML file that was given to the FXMLLoader
  private URL location;

  @FXML // fx:id="pf_password"
  private PasswordField pf_password; // Value injected by FXMLLoader

  @FXML // fx:id="tf_email"
  private TextField tf_email; // Value injected by FXMLLoader

  @FXML // fx:id="button_login"
  private Button button_login; // Value injected by FXMLLoader

  @FXML
  void initialize() {
    assert pf_password !=
    null : "fx:id=\"pf_password\" was not injected: check your FXML file 'log-in.fxml'.";
    assert tf_email !=
    null : "fx:id=\"tf_email\" was not injected: check your FXML file 'log-in.fxml'.";
    assert button_login !=
    null : "fx:id=\"button_login\" was not injected: check your FXML file 'log-in.fxml'.";

    button_login.setOnAction(event -> {
      String email = tf_email.getText();
      String password = pf_password.getText();

      try {
        UserDao userDao = new UserDao();
        AuthService authService = new AuthService(userDao);
        authService.authenticate(email, password);
        System.out.println("Authenticated");
      } catch (SQLException e) {
        System.out.println(
          "An error occurred while accessing the database: " + e.getMessage()
        );
      } catch (AuthenticationException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    });
  }
}
