package view.tables;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import model.Appointment;

public class AppointmentTableModel extends AbstractTableModel {

    private final String[] columns = {
            "Appointment ID", "Patient ID", "Clinician ID", "Facility ID",
            "Date", "Time", "Status"
    };

    private final List<Appointment> appointments;

    public AppointmentTableModel(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public int getRowCount() { return appointments.size(); }

    @Override
    public int getColumnCount() { return columns.length; }

    @Override
    public String getColumnName(int column) { return columns[column]; }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Appointment a = appointments.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> a.getAppointmentId();
            case 1 -> a.getPatientId();
            case 2 -> a.getClinicianId();
            case 3 -> a.getFacilityId();
            case 4 -> a.getAppointmentDate();
            case 5 -> a.getAppointmentTime();
            case 6 -> a.getStatus();
            default -> "";
        };
    }
}
