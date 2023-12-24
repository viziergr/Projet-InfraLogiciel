package com.timefusion.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginPanel extends JPanel {

  private JTextField emailField;
  private JPasswordField passwordField;
  private JButton loginButton;

  public LoginPanel() {
    setLayout(new GridBagLayout()); // Utilisez un gestionnaire de mise en page GridBagLayout

    // Créez des contraintes pour configurer le placement des composants
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.gridx = 0; // Colonne 0
    constraints.gridy = 0; // Ligne 0
    constraints.insets = new Insets(5, 5, 5, 5); // Marge entre les composants

    emailField = new JTextField(20);
    add(new JLabel("Email:"), constraints); // Ajoutez le label "Email"
    constraints.gridy++; // Passez à la ligne suivante
    add(emailField, constraints); // Ajoutez le champ "Email"

    constraints.gridy++; // Passez à la ligne suivante
    passwordField = new JPasswordField(20);
    add(new JLabel("Mot de passe:"), constraints); // Ajoutez le label "Mot de passe"
    constraints.gridy++; // Passez à la ligne suivante
    add(passwordField, constraints); // Ajoutez le champ "Mot de passe"

    constraints.gridy++; // Passez à la ligne suivante
    loginButton = new JButton("Login");
    add(loginButton, constraints); // Ajoutez le bouton "Login"

    loginButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          String email = emailField.getText();
          String password = new String(passwordField.getPassword());

          // Appelez la classe de gestion des données pour vérifier les informations de connexion
          if (validateLogin(email, password)) {
            // Les informations de connexion sont correctes, accédez au tableau de bord
            openDashboard();
          } else {
            // Les informations de connexion sont incorrectes, affichez un message d'erreur
            JOptionPane.showMessageDialog(
              LoginPanel.this,
              "Identifiants incorrects !"
            );
          }
        }
      }
    );
  }

  // Méthode pour vérifier les informations de connexion
  private boolean validateLogin(String email, String password) {
    // Implémentez votre logique de validation des identifiants ici
    // Cette méthode devrait vérifier les identifiants avec votre source de données (par exemple, une base de données)
    // Si les informations sont correctes, retournez true, sinon retournez false
    // Exemple simplifié :
    return email.equals("user") && password.equals("password");
  }

  // Méthode pour ouvrir le tableau de bord
  private void openDashboard() {
    // Créez une instance du tableau de bord et remplacez le contenu de la fenêtre actuelle par le tableau de bord
    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
    frame.getContentPane().removeAll();
    frame.getContentPane().add(new DashboardPanel());
    frame.revalidate();
    frame.repaint();
  }
}
