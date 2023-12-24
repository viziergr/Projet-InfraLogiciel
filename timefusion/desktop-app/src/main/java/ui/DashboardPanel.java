package main.java.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import main.java.dao.EventDao;
import main.java.model.Event;

public class DashboardPanel extends JPanel {

  private JLabel welcomeLabel;
  private JButton viewTeamsButton;
  private JButton viewEventsButton;
  private JTable agendaTable;
  private DefaultTableModel tableModel;

  public DashboardPanel() {
    welcomeLabel = new JLabel("Welcome to the Dashboard");
    viewTeamsButton = new JButton("View Teams");
    viewEventsButton = new JButton("Add Events");

    // Layout the components
    this.add(welcomeLabel);
    this.add(viewTeamsButton);
    this.add(viewEventsButton);

    viewEventsButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          // Logic to view events
          openEventPanel();
        }
      }
    );

    // Create a table for the schedule
    tableModel =
      new DefaultTableModel(
        new String[] {
          "Date",
          "Heure de début",
          "Heure de fin",
          "Description",
        },
        0
      );
    agendaTable = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(agendaTable);
    this.add(scrollPane);

    displayEvents();
  }

  public void setUserInformation(String username) {
    welcomeLabel.setText("Welcome, " + username);
  }

  private void openEventPanel() {
    // Logic to open events
    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
    frame.getContentPane().removeAll();
    frame.getContentPane().add(new EventPanel());
    frame.revalidate();
    frame.repaint();
  }

  private void displayEvents() {
    // Logic to display events
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
      "yyyy-MM-dd HH:mm"
    );
    try {
      List<Event> allEvents = EventDao.getInstance().getAllEvents();
      for (Event event : allEvents) {
        tableModel.addRow(
          new String[] {
            event
              .getStartTime()
              .getDayOfWeek()
              .getDisplayName(TextStyle.FULL, getLocale()),
            event.getStartTime().format(formatter),
            event.getEndTime().format(formatter),
            event.getDescription(),
          }
        );
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
  // Additional methods for dashboard management
}
