package view.tables;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import model.Prescription;

public class PrescriptionTableModel extends AbstractTableModel {

    private final String[] columns = {
            "Prescription ID", "Patient ID", "Clinician ID",
            "Medication", "Dosage", "Frequency"
    };

    private final List<Prescription> prescriptions;

    public PrescriptionTableModel(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    @Override
    public int getRowCount() { return prescriptions.size(); }

    @Override
    public int getColumnCount() { return columns.length; }

    @Override
    public String getColumnName(int column) { return columns[column]; }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Prescription p = prescriptions.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> p.getPrescriptionId();
            case 1 -> p.getPatientId();
            case 2 -> p.getClinicianId();
            case 3 -> p.getMedicationName();
            case 4 -> p.getDosage();
            case 5 -> p.getFrequency();
            default -> "";
        };
    }
}
