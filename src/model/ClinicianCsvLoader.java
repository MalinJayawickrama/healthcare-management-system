package model;

import util.CsvReader;
import java.util.List;
import java.util.Map;

public class ClinicianCsvLoader {

    public void load(String filename, ClinicianRepository repo) {
        List<Map<String, String>> rows = CsvReader.readAsMaps(filename);

        for (Map<String, String> r : rows) {
            String id = pick(r, "clinician_id", "clinicianId", "id");
            String first = pick(r, "first_name", "firstName");
            String last = pick(r, "last_name", "lastName");
            String role = pick(r, "speciality", "specialty", "role");
            String title = pick(r, "title");
            if (!title.isEmpty()) role = title + (role.isEmpty() ? "" : " - " + role);
            String phone = pick(r, "phone_number", "phone", "phoneNumber");
            String email = pick(r, "email");
            String facility = pick(r, "workplace_id", "facility_id", "facilityId", "gp_surgery_id", "gpSurgeryId");

            repo.add(new Clinician(id, first, last, role, phone, email, facility));
        }
    }

    private String pick(Map<String, String> row, String... keys) {
        for (String k : keys) {
            if (row.containsKey(k)) return row.get(k);
        }
        return "";
    }
}
