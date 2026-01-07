package view.panels;

import controller.AppController;
import model.PatientRepository;
import view.tables.PatientTableModel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;

public class PatientsPanel extends JPanel {

    private final AppController controller;
    private final PatientRepository repo;
    private final PatientTableModel tableModel;

    public PatientsPanel(AppController controller) {
        this.controller = controller;
        this.repo = controller.getPatientRepository();

        setLayout(new BorderLayout());

        tableModel = new PatientTableModel(repo.getAll());
        JTable table = new JTable(tableModel);

        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
