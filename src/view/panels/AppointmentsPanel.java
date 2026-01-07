package view.panels;

import controller.AppController;
import model.Appointment;
import model.AppointmentRepository;
import view.tables.AppointmentTableModel;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class AppointmentsPanel extends JPanel {

    private final AppController controller;
    private final AppointmentRepository repo;
    private final AppointmentTableModel tableModel;
    private JTable table;

    public AppointmentsPanel(AppController controller) {
        this.controller = controller;
        this.repo = controller.getAppointmentRepository();

        setLayout(new BorderLayout());

        tableModel = new AppointmentTableModel(repo.getAll());
        table = new JTable(tableModel);

        add(buildTopBar(), BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private JPanel buildTopBar() {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton add = new JButton("Add Appointment");
        add.addActionListener(e -> addAppointment());

        JButton edit = new JButton("Edit Selected");
        edit.addActionListener(e -> editSelected());

        JButton del = new JButton("Delete Selected");
        del.addActionListener(e -> deleteSelected());

        bar.add(add);
        bar.add(edit);
        bar.add(del);
        return bar;
    }

    private void addAppointment() {
        JTextField patientId = new JTextField(10);
        JTextField clinicianId = new JTextField(10);
        JTextField facilityId = new JTextField(10);
        JTextField date = new JTextField(10);
        JTextField time = new JTextField(8);
        JTextField status = new JTextField(10);
        JTextArea notes = new JTextArea(3, 20);

        JPanel form = form(patientId, clinicianId, facilityId, date, time, status, notes);

        int r = JOptionPane.showConfirmDialog(this, form, "Add Appointment",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (r != JOptionPane.OK_OPTION) return;

        controller.addAppointment(
                patientId.getText(),
                clinicianId.getText(),
                facilityId.getText(),
                date.getText(),
                time.getText(),
                status.getText(),
                notes.getText()
        );

        tableModel.fireTableDataChanged();
    }

    private void editSelected() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Select an appointment first.");
            return;
        }

        String id = String.valueOf(tableModel.getValueAt(row, 0));
        Appointment a = repo.findById(id);
        if (a == null) return;

        JTextField patientId = new JTextField(a.getPatientId(), 10);
        JTextField clinicianId = new JTextField(a.getClinicianId(), 10);
        JTextField facilityId = new JTextField(a.getFacilityId(), 10);
        JTextField date = new JTextField(a.getAppointmentDate(), 10);
        JTextField time = new JTextField(a.getAppointmentTime(), 8);
        JTextField status = new JTextField(a.getStatus(), 10);
        JTextArea notes = new JTextArea(a.getNotes(), 3, 20);

        JPanel form = form(patientId, clinicianId, facilityId, date, time, status, notes);
        form.add(row("Appointment ID:", new JLabel(a.getAppointmentId())));

        int r = JOptionPane.showConfirmDialog(this, form, "Edit Appointment",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (r != JOptionPane.OK_OPTION) return;

        Appointment updated = new Appointment(
                a.getAppointmentId(),
                patientId.getText(),
                clinicianId.getText(),
                facilityId.getText(),
                date.getText(),
                time.getText(),
                status.getText(),
                notes.getText()
        );

        controller.updateAppointment(updated);
        tableModel.fireTableDataChanged();
    }

    private void deleteSelected() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Select an appointment first.");
            return;
        }

        String id = String.valueOf(tableModel.getValueAt(row, 0));

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Delete appointment " + id + "?",
                "Confirm delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) return;

        controller.deleteAppointmentById(id);
        tableModel.fireTableDataChanged();
    }

    private JPanel form(JTextField patientId, JTextField clinicianId, JTextField facilityId,
                        JTextField date, JTextField time, JTextField status, JTextArea notes) {

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));

        form.add(row("Patient ID:", patientId));
        form.add(row("Clinician ID:", clinicianId));
        form.add(row("Facility ID:", facilityId));
        form.add(row("Date:", date));
        form.add(row("Time:", time));
        form.add(row("Status:", status));
        form.add(new JLabel("Notes:"));
        form.add(new JScrollPane(notes));

        return form;
    }

    private JPanel row(String label, JComponent field) {
        JPanel r = new JPanel(new FlowLayout(FlowLayout.LEFT));
        r.add(new JLabel(label));
        r.add(field);
        return r;
    }
}
