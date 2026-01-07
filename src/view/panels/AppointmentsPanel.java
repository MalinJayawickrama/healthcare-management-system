package view.panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import controller.AppController;

public class AppointmentsPanel extends JPanel {

    private final AppController controller;

    public AppointmentsPanel(AppController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());
        add(new JLabel("Appointments panel (Phase D/E: CRUD)"), BorderLayout.CENTER);
    }
}
