package view.panels;

import controller.AppController;
import model.ReferralRepository;
import view.tables.ReferralTableModel;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.time.LocalDate;


public class ReferralsPanel extends JPanel {

    private final ReferralTableModel tableModel;
    private JTable table;

    public ReferralsPanel(AppController controller) {
        ReferralRepository repo = controller.getReferralRepository();

        setLayout(new BorderLayout());

        tableModel = new ReferralTableModel(repo.getAll());
        table = new JTable(tableModel);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buildTopBar(controller), BorderLayout.NORTH);
    }

    private JPanel buildTopBar(AppController controller) {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton addBtn = new JButton("Add Referral");
        addBtn.addActionListener(e -> showAddDialog(controller));

        JButton editBtn = new JButton("Edit Selected");
        editBtn.addActionListener(e -> showEditDialog(controller));

        JButton delBtn = new JButton("Delete Selected");
        delBtn.addActionListener(e -> deleteSelected(controller));

        bar.add(addBtn);
        bar.add(editBtn);
        bar.add(delBtn);

        return bar;
    }

    private void showAddDialog(AppController controller) {
        JTextField patientId = new JTextField(10);
        JTextField referringClinicianId = new JTextField(10);
        JTextField referredToClinicianId = new JTextField(10);
        JTextField referringFacilityId = new JTextField(10);
        JTextField referredToFacilityId = new JTextField(10);
        JTextField urgency = new JTextField(10);
        JTextField status = new JTextField(10);
        JTextField appointmentId = new JTextField(10);

        JTextArea reason = new JTextArea(3, 28);
        JTextArea summary = new JTextArea(4, 28);
        JTextArea investigations = new JTextArea(3, 28);
        JTextArea notes = new JTextArea(3, 28);

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));

        form.add(row("Patient ID:", patientId));
        form.add(row("Referring Clinician ID:", referringClinicianId));
        form.add(row("Referred To Clinician ID:", referredToClinicianId));
        form.add(row("Referring Facility ID:", referringFacilityId));
        form.add(row("Referred To Facility ID:", referredToFacilityId));
        form.add(row("Urgency:", urgency));
        form.add(row("Status:", status));
        form.add(row("Appointment ID:", appointmentId));

        form.add(new JLabel("Referral Reason:"));
        form.add(new JScrollPane(reason));
        form.add(new JLabel("Clinical Summary:"));
        form.add(new JScrollPane(summary));
        form.add(new JLabel("Requested Investigations:"));
        form.add(new JScrollPane(investigations));
        form.add(new JLabel("Notes:"));
        form.add(new JScrollPane(notes));

        int result = JOptionPane.showConfirmDialog(
                this, form, "Add Referral",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) return;

        if (patientId.getText().trim().isEmpty() || referringClinicianId.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Patient ID and referring clinician ID are required.");
            return;
        }

        controller.addReferral(
                patientId.getText(),
                referringClinicianId.getText(),
                referredToClinicianId.getText(),
                referringFacilityId.getText(),
                referredToFacilityId.getText(),
                urgency.getText(),
                reason.getText(),
                summary.getText(),
                investigations.getText(),
                status.getText(),
                appointmentId.getText(),
                notes.getText()
        );

        tableModel.fireTableDataChanged();
        JOptionPane.showMessageDialog(this, "Referral saved. Output file written to /out.");
    }

    private JPanel row(String label, JComponent field) {
        JPanel r = new JPanel(new FlowLayout(FlowLayout.LEFT));
        r.add(new JLabel(label));
        r.add(field);
        return r;
    }
private void showEditDialog(AppController controller) {
    int row = table.getSelectedRow();
    if (row < 0) {
        JOptionPane.showMessageDialog(this, "Select a referral first.");
        return;
    }

    String referralId = String.valueOf(tableModel.getValueAt(row, 0));
    var repo = controller.getReferralRepository();
    var existing = repo.findById(referralId);
    if (existing == null) return;

    JTextField patientId = new JTextField(existing.getPatientId(), 10);
    JTextField referringClinicianId = new JTextField(existing.getReferringClinicianId(), 10);
    JTextField referredToClinicianId = new JTextField(existing.getReferredToClinicianId(), 10);
    JTextField referringFacilityId = new JTextField(existing.getReferringFacilityId(), 10);
    JTextField referredToFacilityId = new JTextField(existing.getReferredToFacilityId(), 10);
    JTextField urgency = new JTextField(existing.getUrgencyLevel(), 10);
    JTextField status = new JTextField(existing.getStatus(), 10);
    JTextField appointmentId = new JTextField(existing.getAppointmentId(), 10);

    JTextArea reason = new JTextArea(existing.getReferralReason(), 3, 28);
    JTextArea summary = new JTextArea(existing.getClinicalSummary(), 4, 28);
    JTextArea investigations = new JTextArea(existing.getRequestedInvestigations(), 3, 28);
    JTextArea notes = new JTextArea(existing.getNotes(), 3, 28);

    JPanel form = new JPanel();
    form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));

    form.add(row("Referral ID:", new JLabel(existing.getReferralId())));
    form.add(row("Patient ID:", patientId));
    form.add(row("Referring Clinician ID:", referringClinicianId));
    form.add(row("Referred To Clinician ID:", referredToClinicianId));
    form.add(row("Referring Facility ID:", referringFacilityId));
    form.add(row("Referred To Facility ID:", referredToFacilityId));
    form.add(row("Urgency:", urgency));
    form.add(row("Status:", status));
    form.add(row("Appointment ID:", appointmentId));

    form.add(new JLabel("Referral Reason:"));
    form.add(new JScrollPane(reason));
    form.add(new JLabel("Clinical Summary:"));
    form.add(new JScrollPane(summary));
    form.add(new JLabel("Requested Investigations:"));
    form.add(new JScrollPane(investigations));
    form.add(new JLabel("Notes:"));
    form.add(new JScrollPane(notes));

    int result = JOptionPane.showConfirmDialog(
            this, form, "Edit Referral",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
    );

    if (result != JOptionPane.OK_OPTION) return;

    String today = LocalDate.now().toString();

    var updated = new model.Referral(
            existing.getReferralId(),
            patientId.getText().trim(),
            referringClinicianId.getText().trim(),
            referredToClinicianId.getText().trim(),
            referringFacilityId.getText().trim(),
            referredToFacilityId.getText().trim(),
            existing.getReferralDate(), // keep original referral_date
            urgency.getText().trim(),
            reason.getText(),
            summary.getText(),
            investigations.getText(),
            status.getText().trim(),
            appointmentId.getText().trim(),
            notes.getText(),
            existing.getCreatedDate(),  // keep original created_date
            today                       // update last_updated
    );

    controller.updateReferral(updated);
    tableModel.refresh();
}

    private void deleteSelected(AppController controller) {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Select a referral first.");
            return;
        }

        String referralId = String.valueOf(tableModel.getValueAt(row, 0));

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Delete referral " + referralId + "?",
                "Confirm delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) return;

        controller.deleteReferralById(referralId);
        tableModel.refresh();
    }
}
