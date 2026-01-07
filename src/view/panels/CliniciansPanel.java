package view.panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import controller.AppController;

public class CliniciansPanel extends JPanel {

    private final AppController controller;

    public CliniciansPanel(AppController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());
        add(new JLabel("Clinicians panel (Phase C: table display)"), BorderLayout.CENTER);
    }
}
