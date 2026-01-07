package view.panels;

import controller.AppController;
import model.PrescriptionRepository;
import view.tables.PrescriptionTableModel;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class PrescriptionsPanel extends JPanel {

    private final PrescriptionTableModel tableModel;

    public PrescriptionsPanel(AppController controller) {
        PrescriptionRepository repo = controller.getPrescriptionRepository();

        setLayout(new BorderLayout());

        tableModel = new PrescriptionTableModel(repo.getAll());
        JTable table = new JTable(tableModel);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buildTopBar(controller), BorderLayout.NORTH);
    }

    private JPanel buildTopBar(AppController controller) {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton addBtn = new JButton("Add Prescription");
        addBtn.addActionListener(e -> showAddDialog(controller));

        bar.add(addBtn);
        return bar;
    }

    private void showAddDialog(AppController controller) {
        JTextField patientId = new JTextField(10);
        JTextField clinicianId = new JTextField(10);
        JTextField medication = new JTextField(20);
        JTextField dosage = new JTextField(15);
        JTextField frequency = new JTextField(15);
        JTextField instructions = new JTextField(25);

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));

        form.add(row("Patient ID:", patientId));
        form.add(row("Clinician ID:", clinicianId));
        form.add(row("Medication:", medication));
        form.add(row("Dosage:", dosage));
        form.add(row("Frequency:", frequency));
        form.add(row("Instructions:", instructions));

        int result = JOptionPane.showConfirmDialog(
                this,
                form,
                "Add Prescription",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) return;

        if (patientId.getText().trim().isEmpty() ||
            clinicianId.getText().trim().isEmpty() ||
            medication.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(this, "Patient, clinician and medication are required.");
            return;
        }

        controller.addPrescription(
                patientId.getText(),
                clinicianId.getText(),
                medication.getText(),
                dosage.getText(),
                frequency.getText(),
                instructions.getText()
        );

        tableModel.fireTableDataChanged();
    }

    private JPanel row(String label, JComponent field) {
        JPanel r = new JPanel(new FlowLayout(FlowLayout.LEFT));
        r.add(new JLabel(label));
        r.add(field);
        return r;
    }
}
