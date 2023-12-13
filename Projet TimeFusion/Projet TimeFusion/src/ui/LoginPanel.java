package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginPanel() {
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                // Implement login logic
            }
        });

        // Layout the components
        this.add(new JLabel("Email:"));
        this.add(emailField);
        this.add(new JLabel("Password:"));
        this.add(passwordField);
        this.add(loginButton);
    }
}
