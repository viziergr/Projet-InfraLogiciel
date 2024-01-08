package com.timefusion.ui;

import com.timefusion.dao.UserDao;
import com.timefusion.exception.AuthenticationException;
import com.timefusion.service.AuthService;
import com.timefusion.util.ValidationUtil;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class LogInController {

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private PasswordField pf_password;

  @FXML
  private TextField tf_email;

  private Label lbl_emailError;

  @FXML
  private Button button_login;

  private AuthService authService;

  private PauseTransition emailPause;
  private PauseTransition passwordPause;

  public LogInController() throws SQLException {
    this.authService = new AuthService(new UserDao());
  }

  @FXML
  void initialize() {
    assert pf_password !=
    null : "fx:id=\"pf_password\" was not injected: check your FXML file 'log-in.fxml'.";
    assert tf_email !=
    null : "fx:id=\"tf_email\" was not injected: check your FXML file 'log-in.fxml'.";
    assert button_login !=
    null : "fx:id=\"button_login\" was not injected: check your FXML file 'log-in.fxml'.";

    lbl_emailError = new Label();
    lbl_emailError.setTextFill(Color.RED);

    // Add focus listeners to trigger validation on focus lost
    tf_email
      .focusedProperty()
      .addListener((observable, oldValue, newValue) -> {
        if (!newValue) {
          validateEmail();
        }
      });

    pf_password
      .focusedProperty()
      .addListener((observable, oldValue, newValue) -> {
        if (!newValue) {
          validatePassword();
        }
      });

    tf_email
      .parentProperty()
      .addListener((observable, oldValue, newValue) -> {
        if (oldValue != null) {
          oldValue
            .focusedProperty()
            .removeListener((obs, wasFocused, isNowFocused) -> {
              if (!isNowFocused) {
                validateEmail();
              }
            });
        }
        if (newValue != null) {
          newValue
            .focusedProperty()
            .addListener((obs, wasFocused, isNowFocused) -> {
              if (!isNowFocused) {
                validateEmail();
              }
            });
        }
      });

    pf_password
      .parentProperty()
      .addListener((observable, oldValue, newValue) -> {
        if (oldValue != null) {
          oldValue
            .focusedProperty()
            .removeListener((obs, wasFocused, isNowFocused) -> {
              if (!isNowFocused) {
                validatePassword();
              }
            });
        }
        if (newValue != null) {
          newValue
            .focusedProperty()
            .addListener((obs, wasFocused, isNowFocused) -> {
              if (!isNowFocused) {
                validatePassword();
              }
            });
        }
      });

    emailPause = new PauseTransition(Duration.seconds(1));
    emailPause.setOnFinished(event -> validateEmail());

    passwordPause = new PauseTransition(Duration.seconds(1));
    passwordPause.setOnFinished(event -> validatePassword());

    // Button click event
    button_login.setOnAction(event -> {
      validateEmail();
      validatePassword();

      String email = tf_email.getText();
      String password = pf_password.getText();

      // Validate email and password
      Boolean emailValidationResult = ValidationUtil.isValidEmail(email);

      if (emailValidationResult == null || !emailValidationResult) {
        showAlert(
          "Invalid Email",
          "There is no existing account for this email."
        );
        return;
      }

      Boolean passwordValidationResult = ValidationUtil.isValidPassword(
        password
      );

      if (passwordValidationResult == null || !passwordValidationResult) {
        showAlert(
          "Invalid Password",
          "The password is incorrect. Please try again."
        );
        return;
      }

      try {
        authService.authenticate(email, password);
        showSuccessAlert("Authentication successed", "You are now logged in.");
      } catch (AuthenticationException e) {
        showAlert("Authentication Failed", e.getMessage());
      } finally {
        pf_password.clear();
      }
    });
  }

  private void validateEmail() {
    String email = tf_email.getText();
    Boolean result = ValidationUtil.isValidEmail(email);
    if (result != null && !result && !email.isEmpty()) {
      showAlert("Invalid Email", "Please enter a valid email address.");
    }
  }

  private void validatePassword() {
    String password = pf_password.getText();
    Boolean result = ValidationUtil.isValidPassword(password);
    if (result != null && !result && !password.isEmpty()) {
      showAlert(
        "Invalid Password",
        "Password must meet the specified criteria. A valid password must be between 8 and 20 characters in length, containing at least one numeric digit, one uppercase and one lowercase letter, and at least one special character from the set [@#$%^&+=], with no leading or trailing spaces."
      );
    }
  }

  private void showAlert(String title, String content) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
  }

  private void showSuccessAlert(String title, String content) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
  }
}
