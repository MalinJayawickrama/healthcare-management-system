package view.tables;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import model.Patient;

public class PatientTableModel extends AbstractTableModel {

    private final String[] columns = {
            "Patient ID", "First Name", "Last Name", "DOB", "Phone", "Email"
    };

    private List<Patient> patients;

    public PatientTableModel(List<Patient> patients) {
        this.patients = patients;
    }

    @Override
    public int getRowCount() {
        return patients.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Patient p = patients.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> p.getPatientId();
            case 1 -> p.getFirstName();
            case 2 -> p.getLastName();
            case 3 -> p.getDateOfBirth();
            case 4 -> p.getPhoneNumber();
            case 5 -> p.getEmail();
            default -> "";
        };
    }

    public Patient getPatientAt(int rowIndex) {
        return patients.get(rowIndex);
    }

    // Call this after you add/remove patients later
    public void refresh() {
        fireTableDataChanged();
    }
}
