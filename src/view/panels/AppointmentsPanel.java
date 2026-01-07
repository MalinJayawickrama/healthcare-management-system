package view.panels;

import controller.AppController;
import model.AppointmentRepository;
import view.tables.AppointmentTableModel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;

public class AppointmentsPanel extends JPanel {

    public AppointmentsPanel(AppController controller) {
        AppointmentRepository repo = controller.getAppointmentRepository();

        setLayout(new BorderLayout());

        AppointmentTableModel model = new AppointmentTableModel(repo.getAll());
        JTable table = new JTable(model);

        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
