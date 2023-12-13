package ui;

import javax.swing.*;

public class DashboardPanel extends JPanel {

    private JLabel welcomeLabel;
    private JButton viewTeamsButton;
    private JButton viewEventsButton;

    public DashboardPanel() {
        welcomeLabel = new JLabel("Welcome to the Dashboard");
        viewTeamsButton = new JButton("View Teams");
        viewEventsButton = new JButton("View Events");

        // Add action listeners for buttons if needed

        // Layout the components
        this.add(welcomeLabel);
        this.add(viewTeamsButton);
        this.add(viewEventsButton);
    }

    public void setUserInformation(String username) {
        welcomeLabel.setText("Welcome, " + username);
    }

    // Additional methods for dashboard management
}
