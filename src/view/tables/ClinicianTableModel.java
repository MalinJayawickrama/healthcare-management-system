package view.tables;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import model.Clinician;

public class ClinicianTableModel extends AbstractTableModel {

    private final String[] columns = {"Clinician ID", "First Name", "Last Name", "Role", "Phone", "Email", "Facility"};
    private final List<Clinician> clinicians;

    public ClinicianTableModel(List<Clinician> clinicians) {
        this.clinicians = clinicians;
    }

    @Override
    public int getRowCount() { return clinicians.size(); }

    @Override
    public int getColumnCount() { return columns.length; }

    @Override
    public String getColumnName(int column) { return columns[column]; }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Clinician c = clinicians.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> c.getClinicianId();
            case 1 -> c.getFirstName();
            case 2 -> c.getLastName();
            case 3 -> c.getRole();
            case 4 -> c.getPhoneNumber();
            case 5 -> c.getEmail();
            case 6 -> c.getFacilityId();
            default -> "";
        };
    }
}
