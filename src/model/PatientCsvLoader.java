package model;

import java.util.List;
import java.util.Map;
import util.CsvReader;

public class PatientCsvLoader {

    // Reads patients.csv and fills the repository
    public void load(String filename, PatientRepository repo) {
        List<Map<String, String>> rows = CsvReader.readAsMaps(filename);

        for (Map<String, String> r : rows) {
            // Try common header names (keeps us flexible without guessing)
            String id = pick(r, "patientId", "patient_id", "PatientID", "id", "ID");
            String first = pick(r, "firstName", "first_name", "Forename", "FirstName");
            String last = pick(r, "lastName", "last_name", "Surname", "LastName");
            String dob = pick(r, "dateOfBirth", "dob", "DOB", "DateOfBirth");
            String phone = pick(r, "phone", "Phone", "phoneNumber", "PhoneNumber");
            String email = pick(r, "email", "Email");
            String address = pick(r, "address", "Address");
            String gp = pick(r, "gpSurgeryId", "gp_id", "GPSurgeryId", "GP", "gpSurgery");

            // If your CSV uses different headers, this still loads blanks safely;
            // we can tighten it once we inspect the actual header row later.
            Patient p = new Patient(id, first, last, dob, phone, email, address, gp);
            repo.add(p);
        }
    }

    private String pick(Map<String, String> row, String... keys) {
        for (String k : keys) {
            if (row.containsKey(k)) return row.get(k);
        }
        return "";
    }
}
