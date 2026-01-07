package view.panels;

import controller.AppController;
import model.Clinician;
import model.ClinicianRepository;
import view.tables.ClinicianTableModel;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class CliniciansPanel extends JPanel {

    private final AppController controller;
    private final ClinicianRepository repo;
    private final ClinicianTableModel tableModel;
    private JTable table;

    public CliniciansPanel(AppController controller) {
        this.controller = controller;
        this.repo = controller.getClinicianRepository();

        setLayout(new BorderLayout());

        tableModel = new ClinicianTableModel(repo.getAll());
        table = new JTable(tableModel);

        add(buildTopBar(), BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private JPanel buildTopBar() {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton add = new JButton("Add Clinician");
        add.addActionListener(e -> addClinician());

        JButton edit = new JButton("Edit Selected");
        edit.addActionListener(e -> editSelected());

        JButton del = new JButton("Delete Selected");
        del.addActionListener(e -> deleteSelected());

        bar.add(add);
        bar.add(edit);
        bar.add(del);
        return bar;
    }

    private void addClinician() {
        JTextField first = new JTextField(15);
        JTextField last = new JTextField(15);
        JTextField title = new JTextField(10);
        JTextField speciality = new JTextField(15);
        JTextField gmc = new JTextField(12);
        JTextField phone = new JTextField(12);
        JTextField email = new JTextField(18);
        JTextField workplaceId = new JTextField(10);
        JTextField workplaceType = new JTextField(10);
        JTextField status = new JTextField(10);
        JTextField startDate = new JTextField(10);

        JPanel form = form(first,last,title,speciality,gmc,phone,email,workplaceId,workplaceType,status,startDate);

        int r = JOptionPane.showConfirmDialog(this, form, "Add Clinician",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (r != JOptionPane.OK_OPTION) return;
        if (first.getText().trim().isEmpty() || last.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "First and last name are required.");
            return;
        }

        controller.addClinician(first.getText(), last.getText(), title.getText(), speciality.getText(),
                gmc.getText(), phone.getText(), email.getText(),
                workplaceId.getText(), workplaceType.getText(), status.getText(), startDate.getText());

        tableModel.fireTableDataChanged();
    }

    private void editSelected() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Select a clinician first.");
            return;
        }

        String clinicianId = String.valueOf(tableModel.getValueAt(row, 0));
        Clinician existing = repo.findById(clinicianId);
        if (existing == null) return;

        JTextField first = new JTextField(existing.getFirstName(), 15);
        JTextField last = new JTextField(existing.getLastName(), 15);
        JTextField title = new JTextField(existing.getTitle(), 10);
        JTextField speciality = new JTextField(existing.getSpeciality(), 15);
        JTextField gmc = new JTextField(existing.getGmcNumber(), 12);
        JTextField phone = new JTextField(existing.getPhoneNumber(), 12);
        JTextField email = new JTextField(existing.getEmail(), 18);
        JTextField workplaceId = new JTextField(existing.getWorkplaceId(), 10);
        JTextField workplaceType = new JTextField(existing.getWorkplaceType(), 10);
        JTextField status = new JTextField(existing.getEmploymentStatus(), 10);
        JTextField startDate = new JTextField(existing.getStartDate(), 10);

        JPanel form = form(first,last,title,speciality,gmc,phone,email,workplaceId,workplaceType,status,startDate);
        form.add(row("Clinician ID (read-only):", new JLabel(existing.getClinicianId())));

        int r = JOptionPane.showConfirmDialog(this, form, "Edit Clinician",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (r != JOptionPane.OK_OPTION) return;

        Clinician updated = new Clinician(
                existing.getClinicianId(),
                first.getText(), last.getText(), title.getText(), speciality.getText(),
                gmc.getText(), phone.getText(), email.getText(),
                workplaceId.getText(), workplaceType.getText(), status.getText(), startDate.getText()
        );

        controller.updateClinician(updated);
        tableModel.fireTableDataChanged();
    }

    private void deleteSelected() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Select a clinician first.");
            return;
        }

        String clinicianId = String.valueOf(tableModel.getValueAt(row, 0));

        int confirm = JOptionPane.showConfirmDialog(this,
                "Delete clinician " + clinicianId + "?", "Confirm delete",
                JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) return;

        controller.deleteClinicianById(clinicianId);
        tableModel.fireTableDataChanged();
    }

    private JPanel form(JTextField first, JTextField last, JTextField title, JTextField speciality,
                        JTextField gmc, JTextField phone, JTextField email,
                        JTextField workplaceId, JTextField workplaceType,
                        JTextField status, JTextField startDate) {

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));

        form.add(row("First name:", first));
        form.add(row("Last name:", last));
        form.add(row("Title:", title));
        form.add(row("Speciality:", speciality));
        form.add(row("GMC number:", gmc));
        form.add(row("Phone:", phone));
        form.add(row("Email:", email));
        form.add(row("Workplace ID:", workplaceId));
        form.add(row("Workplace Type:", workplaceType));
        form.add(row("Employment Status:", status));
        form.add(row("Start Date:", startDate));

        return form;
    }

    private JPanel row(String label, JComponent field) {
        JPanel r = new JPanel(new FlowLayout(FlowLayout.LEFT));
        r.add(new JLabel(label));
        r.add(field);
        return r;
    }
}
