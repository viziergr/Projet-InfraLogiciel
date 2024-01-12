package com.timefusion.jfxcalendar.controllers;

/**
 * Sample Skeleton for 'AddEventDialog.fxml' Controller Class
 */

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class AddEventDialogController {

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private TextField fromTextField;

  @FXML
  private TextField locationField;

  @FXML
  private TextField titleField;

  @FXML
  private Button publicEventButton;

  @FXML
  private DatePicker dateField;

  @FXML
  private Button addEventButton;

  @FXML
  private Button privateEventButton;

  @FXML
  private TextField toTextField;

  @FXML
  private TextField descriptionTextField;

  private boolean isPrivate;

  /**
   * Sets the location field to the given location
   * @param isPrivate
   */
  public void eventSetPrivate(boolean isPrivate) {
    this.isPrivate = isPrivate;
  }

  /**
   * Returns the location of the event
   * @return boolean isPrivate
   */
  public boolean eventIsPrivate() {
    return isPrivate;
  }

  @FXML
  void mouse(ActionEvent event) {}

  /**
   * Handles the action event when the public event button is clicked.
   * Sets the event as public and updates the UI.
   *
   * @param event the action event triggered by clicking the public event button
   */
  @FXML
  void handlePublicEvent(ActionEvent event) {
    eventSetPrivate(false);
    System.out.println("Public Event " + eventIsPrivate());
    publicEventButton.setStyle(
      "-fx-background-color: #4C95CE; -fx-background-radius: 15; -fx-border-color: #4C95CE;"
    );
    privateEventButton.setStyle(
      "-fx-background-color: rgba(0, 0, 0, 0.3); -fx-background-radius: 15; -fx-border-color: rgba(0, 0, 0, 0.3);"
    );
  }

  /**
   * Handles the action event when the private event button is clicked.
   * Sets the event as private and updates the button styles accordingly.
   *
   * @param event the action event triggered by clicking the private event button
   */
  @FXML
  void handlePrivateEvent(ActionEvent event) {
    eventSetPrivate(true);
    System.out.println("Private Event " + eventIsPrivate());
    privateEventButton.setStyle(
      "-fx-background-color: #81C457; -fx-background-radius: 15; -fx-border-color: #81C457;"
    );
    publicEventButton.setStyle(
      "-fx-background-color: rgba(0, 0, 0, 0.3); -fx-background-radius: 15; -fx-border-color: rgba(0, 0, 0, 0.3);"
    );
  }

  @FXML
  void handleAddEvent(ActionEvent event) {
    // Handle logic for adding the event
  }

  @FXML // This method is called by the FXMLLoader when initialization is complete
  void initialize() {
    assert fromTextField !=
    null : "fx:id=\"fromTextField\" was not injected: check your FXML file 'AddEventDialog.fxml'.";
    assert locationField !=
    null : "fx:id=\"locationField\" was not injected: check your FXML file 'AddEventDialog.fxml'.";
    assert titleField !=
    null : "fx:id=\"titleField\" was not injected: check your FXML file 'AddEventDialog.fxml'.";
    assert publicEventButton !=
    null : "fx:id=\"publicEventButton\" was not injected: check your FXML file 'AddEventDialog.fxml'.";
    assert dateField !=
    null : "fx:id=\"dateField\" was not injected: check your FXML file 'AddEventDialog.fxml'.";
    assert addEventButton !=
    null : "fx:id=\"addEventButton\" was not injected: check your FXML file 'AddEventDialog.fxml'.";
    assert privateEventButton !=
    null : "fx:id=\"privateEventButton\" was not injected: check your FXML file 'AddEventDialog.fxml'.";
    assert toTextField !=
    null : "fx:id=\"toTextField\" was not injected: check your FXML file 'AddEventDialog.fxml'.";
    assert descriptionTextField !=
    null : "fx:id=\"descriptionTextField\" was not injected: check your FXML file 'AddEventDialog.fxml'.";

    addFocusLostListener(fromTextField);
    addFocusLostListener(toTextField);
    addFocusLostListener(titleField);
    addFocusLostListener(locationField);
    addFocusLostListener(descriptionTextField);
  }

  private void addFocusLostListener(TextField textField) {
    textField
      .focusedProperty()
      .addListener((observable, oldValue, newValue) -> {
        if (!newValue) {
          if (textField == fromTextField || textField == toTextField) {
            validateTimeFormat(textField);
          } else if (textField == titleField) {
            validateTitle(textField);
          } else if (textField == locationField) {
            validateLocation(textField);
          } else if (textField == descriptionTextField) {
            validateDescription(textField);
          }
        }
      });
  }

  private void validateTimeFormat(TextField textField) {
    String input = textField.getText().trim();
    if (!input.isEmpty() && !isValidTimeFormat(input)) {
      showErrorAlert(
        "Invalid Time Format",
        "Please enter time in HH:mm format (e.g., 11:00).",
        textField
      );
    }
  }

  private boolean isValidTimeFormat(String input) {
    return input.matches("\\d{1,2}:\\d{2}");
  }

  private void showErrorAlert(
    String title,
    String content,
    TextField textField
  ) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();

    textField.clear();
  }

  private void validateTitle(TextField textField) {
    String input = textField.getText().trim();
    if (input.length() > 100) {
      showErrorAlert(
        "Title is Too Long",
        "Title cannot exceed 100 characters.",
        textField
      );
    }
  }

  private void validateLocation(TextField textField) {
    String input = textField.getText().trim();
    if (input.length() > 50) {
      showErrorAlert(
        "Location is Too Long",
        "Location cannot exceed 50 characters.",
        textField
      );
    }
  }

  private void validateDescription(TextField textField) {
    String input = textField.getText().trim();
    if (input.length() > 150) {
      showErrorAlert(
        "Description is Too Long",
        "Description cannot exceed 150 characters.",
        textField
      );
    }
  }
}
//   @FXML
//   private TabPane tabPane;
//   @FXML
//   private TextField titleField;
//   @FXML
//   private Button optionalEventButton;
//   @FXML
//   private Button standardEventButton;
//   @FXML
//   private Button importantEventButton;
//   @FXML
//   private Button criticalEventButton;
//   @FXML
//   private Tab customTab;
//   @FXML
//   private DatePicker dateField;
//   @FXML
//   private TextArea eventNote1;
//   @FXML
//   private TextArea eventNote2;
//   @FXML
//   private RadioButton everyWeekRB;
//   @FXML
//   private RadioButton everyMonthRB;
//   @FXML
//   private RadioButton everyYearRB;
//   @FXML
//   private CheckBox mondayCB;
//   @FXML
//   private CheckBox tuesdayCB;
//   @FXML
//   private CheckBox wednesdayCB;
//   @FXML
//   private CheckBox thursdayCB;
//   @FXML
//   private CheckBox fridayCB;
//   @FXML
//   private CheckBox saturdayCB;
//   @FXML
//   private CheckBox sundayCB;
//   @FXML
//   private CheckBox startOfTheMonthCB;
//   @FXML
//   private CheckBox endOfTheMonthCB;
//   @FXML
//   private DatePicker yearlyDatePicker;
//   private int eventType;
//   @FXML
//   public void initialize() {
//     addPriorityButtonListeners();
//     addPeriodicChoicesListeners();
//     optionalEventButton.fire();
//     everyWeekRB.fire();
//   }
//   private void addPeriodicChoicesListeners() {
//     everyWeekRB.setOnAction(e -> {
//       resetAndDisableAll();
//       enableDaysOfWeek();
//     });
//     everyMonthRB.setOnAction(e -> {
//       resetAndDisableAll();
//       enableMonthOptions();
//     });
//     everyYearRB.setOnAction(e -> {
//       resetAndDisableAll();
//       yearlyDatePicker.setDisable(false);
//     });
//   }
//   private void enableDaysOfWeek() {
//     mondayCB.setDisable(false);
//     tuesdayCB.setDisable(false);
//     wednesdayCB.setDisable(false);
//     thursdayCB.setDisable(false);
//     fridayCB.setDisable(false);
//     saturdayCB.setDisable(false);
//     sundayCB.setDisable(false);
//   }
//   private void enableMonthOptions() {
//     startOfTheMonthCB.setDisable(false);
//     endOfTheMonthCB.setDisable(false);
//   }
//   private void resetAndDisableAll() {
//     disableAll();
//   }
//   private void disableAll() {
//     disableDaysOfWeek();
//     disableMonthOptions();
//     yearlyDatePicker.setDisable(true);
//   }
//   private void disableDaysOfWeek() {
//     mondayCB.setDisable(true);
//     tuesdayCB.setDisable(true);
//     wednesdayCB.setDisable(true);
//     thursdayCB.setDisable(true);
//     fridayCB.setDisable(true);
//     saturdayCB.setDisable(true);
//     sundayCB.setDisable(true);
//   }
//   private void disableMonthOptions() {
//     startOfTheMonthCB.setDisable(true);
//     endOfTheMonthCB.setDisable(true);
//   }
//   private void addPriorityButtonListeners() {
//     optionalEventButton.setOnAction(e -> setEventType(CalendarEvent.OPTIONAL));
//     standardEventButton.setOnAction(e -> setEventType(CalendarEvent.STANDARD));
//     importantEventButton.setOnAction(e -> setEventType(CalendarEvent.IMPORTANT)
//     );
//     criticalEventButton.setOnAction(e -> setEventType(CalendarEvent.URGENT));
//   }
//   private void setEventType(int type) {
//     cleanSelection();
//     eventType = type;
//     switch (type) {
//       case CalendarEvent.OPTIONAL:
//         optionalEventButton.setStyle(
//           "-fx-background-color : #4C95CE; -fx-background-radius:15;"
//         );
//         break;
//       case CalendarEvent.STANDARD:
//         standardEventButton.setStyle(
//           "-fx-background-color : #81C457; -fx-background-radius:15;"
//         );
//         break;
//       case CalendarEvent.IMPORTANT:
//         importantEventButton.setStyle(
//           "-fx-background-color : #F8D500; -fx-background-radius:15;"
//         );
//         break;
//       case CalendarEvent.URGENT:
//         criticalEventButton.setStyle(
//           "-fx-background-color : #E85569; -fx-background-radius:15;"
//         );
//         break;
//     }
//   }
//   private void cleanSelection() {
//     eventType = -1;
//     optionalEventButton.setStyle(
//       "-fx-background-color : #BDC6CC ; -fx-background-radius:15; "
//     );
//     standardEventButton.setStyle(
//       "-fx-background-color : #BDC6CC ; -fx-background-radius:15;"
//     );
//     importantEventButton.setStyle(
//       "-fx-background-color : #BDC6CC ; -fx-background-radius:15;"
//     );
//     criticalEventButton.setStyle(
//       "-fx-background-color : #BDC6CC ; -fx-background-radius:15;"
//     );
//   }
//   public int getEventType() {
//     return eventType;
//   }
//   public void clear() {
//     cleanSelection();
//     eventNote1.setText("");
//     eventNote2.setText("");
//     titleField.setText("");
//     dateField.setValue(null);
//     yearlyDatePicker.setValue(null);
//     tabPane.getSelectionModel().select(0);
//     optionalEventButton.fire();
//     everyWeekRB.fire();
//   }
//   public CalendarEvent getEvent() {
//     String title = titleField.getText();
//     if (title.isEmpty()) {
//       return null;
//     }
//     CalendarEvent event = null;
//     if (tabPane.getSelectionModel().getSelectedIndex() == 0) {
//       if (dateField.getValue() != null) {
//         event = new CalendarEvent(title, eventType, eventNote1.getText());
//         event.setType(CalendarEvent.ONE_TIME_EVENT);
//         event.setDate(dateField.getValue());
//       }
//     } else {
//       if (everyWeekRB.isSelected() && hasDaysSelected()) {
//         event = new CalendarEvent(title, eventType, eventNote2.getText());
//         event.setType(CalendarEvent.RECURRING_EVENT);
//         event.setPeriodicType(CalendarEvent.PER_WEEK);
//         event.setDaysInWeek(collectDaysInWeek());
//       } else if (everyMonthRB.isSelected() && hasMonthPlaceSelected()) {
//         event = new CalendarEvent(title, eventType, eventNote2.getText());
//         event.setType(CalendarEvent.RECURRING_EVENT);
//         event.setPeriodicType(CalendarEvent.PER_MONTH);
//         if (startOfTheMonthCB.isSelected()) {
//           event.setPlaceInMonth(CalendarEvent.START_OF_MONTH);
//         } else {
//           event.setPlaceInMonth(CalendarEvent.END_OF_MONTH);
//         }
//       } else if (yearlyDatePicker.getValue() != null) {
//         event = new CalendarEvent(title, eventType, eventNote2.getText());
//         event.setType(CalendarEvent.RECURRING_EVENT);
//         event.setPeriodicType(CalendarEvent.PER_YEAR);
//         event.setYearlyDate(yearlyDatePicker.getValue());
//       }
//     }
//     return event;
//   }
//   private boolean hasMonthPlaceSelected() {
//     return startOfTheMonthCB.isSelected() || endOfTheMonthCB.isSelected();
//   }
//   private String collectDaysInWeek() {
//     StringBuilder days = new StringBuilder();
//     if (mondayCB.isSelected()) days.append("1,");
//     if (tuesdayCB.isSelected()) days.append("2,");
//     if (wednesdayCB.isSelected()) days.append("3,");
//     if (thursdayCB.isSelected()) days.append("4,");
//     if (fridayCB.isSelected()) days.append("5,");
//     if (saturdayCB.isSelected()) days.append("6,");
//     if (sundayCB.isSelected()) days.append("7,");
//     return days.toString();
//   }
//   private boolean hasDaysSelected() {
//     return (
//       mondayCB.isSelected() ||
//       tuesdayCB.isSelected() ||
//       wednesdayCB.isSelected() ||
//       thursdayCB.isSelected() ||
//       fridayCB.isSelected() ||
//       saturdayCB.isSelected() ||
//       sundayCB.isSelected()
//     );
//   }
// }
