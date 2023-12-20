package main.java.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TeamPanel extends JPanel {

  private JTextField teamNameField;
  private JList<String> teamMembersList;
  private JButton createTeamButton;

  public TeamPanel() {
    teamNameField = new JTextField(20);
    teamMembersList = new JList<>();
    createTeamButton = new JButton("Create Team");

    createTeamButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          // Logic to create a team
        }
      }
    );

    // Layout the components
    this.add(new JLabel("Team Name:"));
    this.add(teamNameField);
    this.add(new JScrollPane(teamMembersList));
    this.add(createTeamButton);
  }
  // Additional methods for team management
}
