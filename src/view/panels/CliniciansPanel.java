package view.panels;

import controller.AppController;
import model.ClinicianRepository;
import view.tables.ClinicianTableModel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;

public class CliniciansPanel extends JPanel {

    public CliniciansPanel(AppController controller) {
        ClinicianRepository repo = controller.getClinicianRepository();

        setLayout(new BorderLayout());

        ClinicianTableModel model = new ClinicianTableModel(repo.getAll());
        JTable table = new JTable(model);

        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
