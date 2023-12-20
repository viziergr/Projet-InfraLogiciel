package main.java.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ProfilePanel extends JPanel {

  private JTextField userNameField;
  private JTextField userEmailField;
  private JPasswordField userPasswordField;
  private JButton updateProfileButton;

  public ProfilePanel() {
    userNameField = new JTextField(20);
    userEmailField = new JTextField(20);
    userPasswordField = new JPasswordField(20);
    updateProfileButton = new JButton("Update Profile");

    updateProfileButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          // Logic to update user profile
        }
      }
    );

    // Layout the components
    this.add(new JLabel("Name:"));
    this.add(userNameField);
    this.add(new JLabel("Email:"));
    this.add(userEmailField);
    this.add(new JLabel("Password:"));
    this.add(userPasswordField);
    this.add(updateProfileButton);
  }
  // Additional methods for profile management
}
