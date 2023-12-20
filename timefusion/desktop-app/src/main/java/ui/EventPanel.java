package main.java.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
//import org.jdesktop.swingx.JXDatePicker; // Importez JXDatePicker depuis la bibliothèque SwingX
import javax.swing.*;
import main.java.dao.EventDao;
import main.java.model.Event;
import main.java.model.User;

public class EventPanel extends JPanel {

  private JTextField eventNameField;
  private JTextField eventDateField;
  //private JXDatePicker eventDatePicker; // Utilisation de JXDatePicker de SwingX
  private JTextArea eventDescription;
  private JButton addEventButton;
  private JButton backDashButton;

  public EventPanel() {
    eventNameField = new JTextField(20);
    eventDateField = new JTextField(20);
    //eventDatePicker = new JXDatePicker(); // Utilisation de JXDatePicker de SwingX
    eventDescription = new JTextArea(5, 20);
    addEventButton = new JButton("Add Event");
    backDashButton = new JButton("Back to Dashboard");

    backDashButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          // Logic to view events
          backDashPanel();
        }
      }
    );

    addEventButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          // Logique pour ajouter un événement
          String name = eventNameField.getText();
          LocalDateTime startTime = LocalDateTime.now();
          LocalDateTime endTime = LocalDateTime.now();
          String description = eventDescription.getText();
          Long id = (long) 1;
          User creator = new User(id, "test", "test", "test", "test");
          Event event = new Event(
            id,
            name,
            startTime,
            endTime,
            "ici",
            description,
            false,
            creator
          );
          EventDao.getInstance().saveEvent(event);
        }
      }
    );

    // Disposition des composants
    this.add(new JLabel("Nom de l'événement:"));
    this.add(eventNameField);
    this.add(new JLabel("Date de l'événement:"));
    //this.add(eventDatePicker);
    this.add(eventDateField);
    this.add(new JLabel("Description:"));
    this.add(new JScrollPane(eventDescription));
    this.add(addEventButton);
    this.add(backDashButton);
  }

  private void backDashPanel() {
    // Logic to open events
    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
    frame.getContentPane().removeAll();
    frame.getContentPane().add(new DashboardPanel());
    frame.revalidate();
    frame.repaint();
  }
  // Méthodes supplémentaires pour la gestion des événements
}
