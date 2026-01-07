package view.panels;

import controller.AppController;
import model.Prescription;
import model.PrescriptionRepository;
import view.tables.PrescriptionTableModel;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class PrescriptionsPanel extends JPanel {

    private final AppController controller;
    private final PrescriptionRepository repo;
    private final PrescriptionTableModel tableModel;
    private JTable table;

    public PrescriptionsPanel(AppController controller) {
        this.controller = controller;
        this.repo = controller.getPrescriptionRepository();

        setLayout(new BorderLayout());

        tableModel = new PrescriptionTableModel(repo.getAll());
        table = new JTable(tableModel);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buildTopBar(), BorderLayout.NORTH);
    }

    private JPanel buildTopBar() {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton addBtn = new JButton("Add Prescription");
        addBtn.addActionListener(e -> showAddDialog());

        JButton editBtn = new JButton("Edit Selected");
        editBtn.addActionListener(e -> showEditDialog());

        JButton delBtn = new JButton("Delete Selected");
        delBtn.addActionListener(e -> deleteSelected());

        bar.add(addBtn);
        bar.add(editBtn);
        bar.add(delBtn);
        return bar;
    }

    private void showAddDialog() {
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
                this, form, "Add Prescription",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
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

        tableModel.refresh();
    }

    private void showEditDialog() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Select a prescription first.");
            return;
        }

        String id = String.valueOf(tableModel.getValueAt(row, 0));
        Prescription existing = repo.findById(id);
        if (existing == null) return;

        JTextField patientId = new JTextField(existing.getPatientId(), 10);
        JTextField clinicianId = new JTextField(existing.getClinicianId(), 10);
        JTextField medication = new JTextField(existing.getMedicationName(), 20);
        JTextField dosage = new JTextField(existing.getDosage(), 15);
        JTextField frequency = new JTextField(existing.getFrequency(), 15);
        JTextField instructions = new JTextField(existing.getInstructions(), 25);

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));

        form.add(row("Prescription ID:", new JLabel(existing.getPrescriptionId())));
        form.add(row("Patient ID:", patientId));
        form.add(row("Clinician ID:", clinicianId));
        form.add(row("Medication:", medication));
        form.add(row("Dosage:", dosage));
        form.add(row("Frequency:", frequency));
        form.add(row("Instructions:", instructions));

        int result = JOptionPane.showConfirmDialog(
                this, form, "Edit Prescription",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) return;

        Prescription updated = new Prescription(
                existing.getPrescriptionId(),
                patientId.getText().trim(),
                clinicianId.getText().trim(),
                medication.getText().trim(),
                dosage.getText().trim(),
                frequency.getText().trim(),
                instructions.getText().trim()
        );

        controller.updatePrescription(updated);
        tableModel.refresh();
    }

    private void deleteSelected() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Select a prescription first.");
            return;
        }

        String id = String.valueOf(tableModel.getValueAt(row, 0));

        int confirm = JOptionPane.showConfirmDialog(
                this, "Delete prescription " + id + "?",
                "Confirm delete", JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) return;

        controller.deletePrescriptionById(id);
        tableModel.refresh();
    }

    private JPanel row(String label, JComponent field) {
        JPanel r = new JPanel(new FlowLayout(FlowLayout.LEFT));
        r.add(new JLabel(label));
        r.add(field);
        return r;
    }
}
