package view.panels;

import controller.AppController;
import model.PatientRepository;
import view.tables.PatientTableModel;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

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
        add(buildTopBar(), BorderLayout.NORTH);
    }

    private JPanel buildTopBar() {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton addBtn = new JButton("Add Patient");
        addBtn.addActionListener(e -> showAddPatientDialog());

        bar.add(addBtn);
        return bar;
    }

    private void showAddPatientDialog() {
        JTextField firstName = new JTextField(15);
        JTextField lastName = new JTextField(15);
        JTextField dob = new JTextField(10);       // keep as text (CSV-safe)
        JTextField phone = new JTextField(12);
        JTextField email = new JTextField(18);
        JTextField address = new JTextField(20);
        JTextField postcode = new JTextField(10);
        JTextField gpSurgeryId = new JTextField(10);

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));

        form.add(row("First name:", firstName));
        form.add(row("Last name:", lastName));
        form.add(row("Date of birth:", dob));
        form.add(row("Phone:", phone));
        form.add(row("Email:", email));
        form.add(row("Address:", address));
        form.add(row("Postcode:", postcode));
        form.add(row("GP surgery ID:", gpSurgeryId));

        int result = JOptionPane.showConfirmDialog(
                this,
                form,
                "Add Patient",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            if (firstName.getText().trim().isEmpty() || lastName.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "First name and last name are required.");
                return;
            }

            controller.addPatient(
                    firstName.getText(),
                    lastName.getText(),
                    dob.getText(),
                    phone.getText(),
                    email.getText(),
                    address.getText(),
                    postcode.getText(),
                    gpSurgeryId.getText()
            );

            tableModel.refresh();
        }
    }

    private JPanel row(String label, JComponent field) {
        JPanel r = new JPanel(new FlowLayout(FlowLayout.LEFT));
        r.add(new JLabel(label));
        r.add(field);
        return r;
    }
}
