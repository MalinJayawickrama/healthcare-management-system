package view;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import controller.AppController;
import view.panels.AppointmentsPanel;
import view.panels.CliniciansPanel;
import view.panels.PatientsPanel;
import view.panels.PrescriptionsPanel;
import view.panels.ReferralsPanel;

public class MainFrame extends JFrame {

    private final AppController controller;

    public MainFrame(AppController controller) {
        this.controller = controller;

        setTitle("Healthcare Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 700);
        setLocationRelativeTo(null);

        setContentPane(buildTabs());
    }

    private JTabbedPane buildTabs() {
        JTabbedPane tabs = new JTabbedPane();

        tabs.addTab("Patients", new PatientsPanel(controller));
        tabs.addTab("Clinicians", new CliniciansPanel(controller));
        tabs.addTab("Appointments", new AppointmentsPanel(controller));
        tabs.addTab("Prescriptions", new PrescriptionsPanel(controller));
        tabs.addTab("Referrals", new ReferralsPanel(controller));

        return tabs;
    }
}
