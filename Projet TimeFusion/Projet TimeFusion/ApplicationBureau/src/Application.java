import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Application {

    private JFrame frame;
    private JTable agendaTable;
    private DefaultTableModel tableModel;
    private JTextField dateField;
    private JTextField heureDebutField;
    private JTextField heureFinField;
    private JTextField descriptionField;
    private JButton addButton;

    private List<Evenement> evenements = new ArrayList<>();

    public Application() {
        frame = new JFrame("Mon Agenda");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Créez un tableau pour l'emploi du temps
        tableModel = new DefaultTableModel(new String[]{"Date", "Heure de début", "Heure de fin", "Description"}, 0);
        agendaTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(agendaTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));

        dateField = new JTextField();
        inputPanel.add(new JLabel("Date :"));
        inputPanel.add(dateField);

        heureDebutField = new JTextField();
        inputPanel.add(new JLabel("Heure de début :"));
        inputPanel.add(heureDebutField);

        heureFinField = new JTextField();
        inputPanel.add(new JLabel("Heure de fin :"));
        inputPanel.add(heureFinField);

        descriptionField = new JTextField();
        inputPanel.add(new JLabel("Description :"));
        inputPanel.add(descriptionField);

        addButton = new JButton("Ajouter");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajouterEvenement();
            }
        });
        inputPanel.add(addButton);

        panel.add(inputPanel, BorderLayout.SOUTH);

        frame.add(panel);
    }

    public void afficher() {
        frame.setVisible(true);
    }

    private void ajouterEvenement() {
        String date = dateField.getText();
        String heureDebut = heureDebutField.getText();
        String heureFin = heureFinField.getText();
        String description = descriptionField.getText();

        if (!date.isEmpty() && !heureDebut.isEmpty() && !heureFin.isEmpty() && !description.isEmpty()) {
            Evenement event = new Evenement(date, heureDebut, heureFin, description);
            evenements.add(event);
            tableModel.addRow(new String[]{event.getDate(), event.getHeureDebut(), event.getHeureFin(), event.getDescription()});
            clearInputFields();
        }
    }

    private void clearInputFields() {
        dateField.setText("");
        heureDebutField.setText("");
        heureFinField.setText("");
        descriptionField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Application agendaApp = new Application();
                agendaApp.afficher();
            }
        });
    }
}

