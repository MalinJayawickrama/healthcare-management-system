package view.panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import controller.AppController;

public class PatientsPanel extends JPanel {

    private final AppController controller;

    public PatientsPanel(AppController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());
        add(new JLabel("Patients panel (Phase C: table + add/edit/delete)"), BorderLayout.CENTER);
    }
}
