package view.panels;

import controller.AppController;
import model.PrescriptionRepository;
import view.tables.PrescriptionTableModel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;

public class PrescriptionsPanel extends JPanel {

    public PrescriptionsPanel(AppController controller) {
        PrescriptionRepository repo = controller.getPrescriptionRepository();

        setLayout(new BorderLayout());

        PrescriptionTableModel model = new PrescriptionTableModel(repo.getAll());
        JTable table = new JTable(model);

        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
