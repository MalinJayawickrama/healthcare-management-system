package model;

import util.CsvReader;
import java.util.List;
import java.util.Map;

public class AppointmentCsvLoader {

    public void load(String filename, AppointmentRepository repo) {
        List<Map<String, String>> rows = CsvReader.readAsMaps(filename);

        for (Map<String, String> r : rows) {
            Appointment a = new Appointment(
                    r.get("appointment_id"),
                    r.get("patient_id"),
                    r.get("clinician_id"),
                    r.get("facility_id"),          // ✅ exact header
                    r.get("appointment_date"),
                    r.get("appointment_time"),
                    r.get("status"),
                    r.get("notes")
            );
            repo.add(a);
        }
    }
}
