package view.panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import controller.AppController;

public class ReferralsPanel extends JPanel {

    private final AppController controller;

    public ReferralsPanel(AppController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());
        add(new JLabel("Referrals panel (Phase F: Singleton ReferralManager + output file)"), BorderLayout.CENTER);
    }
}
