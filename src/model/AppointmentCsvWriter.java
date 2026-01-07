package model;

import java.io.FileWriter;
import java.util.List;

public class AppointmentCsvWriter {

    public void writeAll(String filename, List<Appointment> appointments) {
        try {
            FileWriter fw = new FileWriter(filename, false);

            fw.write("appointment_id,patient_id,clinician_id,facility_id,appointment_date,appointment_time,status,notes");

            for (Appointment a : appointments) {
                fw.write(System.lineSeparator());
                fw.write(
                        csv(a.getAppointmentId()) + "," +
                        csv(a.getPatientId()) + "," +
                        csv(a.getClinicianId()) + "," +
                        csv(a.getFacilityId()) + "," +
                        csv(a.getAppointmentDate()) + "," +
                        csv(a.getAppointmentTime()) + "," +
                        csv(a.getStatus()) + "," +
                        csv(a.getNotes())
                );
            }
            fw.close();
        } catch (Exception e) {
            System.out.println("Failed to write appointments file: " + e.getMessage());
        }
    }

    private String csv(String s) {
        if (s == null) return "";
        if (s.contains(",") || s.contains("\"")) {
            s = s.replace("\"", "\"\"");
            return "\"" + s + "\"";
        }
        return s;
    }
}
