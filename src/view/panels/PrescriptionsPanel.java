package view.panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import controller.AppController;

public class PrescriptionsPanel extends JPanel {

    private final AppController controller;

    public PrescriptionsPanel(AppController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());
        add(new JLabel("Prescriptions panel (Phase D/E: create + save to file)"), BorderLayout.CENTER);
    }
}
