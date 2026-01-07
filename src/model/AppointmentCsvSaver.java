package model;

import java.io.FileWriter;

public class AppointmentCsvSaver {

    public void append(String filename, Appointment a) {
        try {
            FileWriter fw = new FileWriter(filename, true);
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
            fw.close();
        } catch (Exception e) {
            System.out.println("Failed to append appointment: " + e.getMessage());
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
