package ui;

import javax.swing.*;

public class MainApplication {

  private JFrame mainFrame;
  private LoginPanel loginPanel;
  private DashboardPanel dashboardPanel;

  public MainApplication() {
    mainFrame = new JFrame("Agenda and Team Management Application");
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setSize(400, 300);

    // Initialize panels
    loginPanel = new LoginPanel();
    dashboardPanel = new DashboardPanel();

    // Add panels to the frame
    mainFrame.add(loginPanel); // Start with the login panel

    // Show the frame
    mainFrame.setVisible(true);
  }

  // Method to switch between panels
  public void showDashboard() {
    mainFrame.remove(loginPanel);
    mainFrame.add(dashboardPanel);
    mainFrame.revalidate();
    mainFrame.repaint();
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(
      new Runnable() {
        public void run() {
          new MainApplication();
        }
      }
    );
  }
}
