package model;

import java.util.List;
import java.util.Map;
import util.CsvReader;

public class PatientCsvLoader {

    public void load(String filename, PatientRepository repo) {
        List<Map<String, String>> rows = CsvReader.readAsMaps(filename);

        for (Map<String, String> r : rows) {
            Patient p = new Patient(
                    r.get("patient_id"),
                    r.get("first_name"),
                    r.get("last_name"),
                    r.get("date_of_birth"),
                    r.get("nhs_number"),
                    r.get("gender"),
                    r.get("phone_number"),
                    r.get("email"),
                    r.get("address"),
                    r.get("postcode"),
                    r.get("emergency_contact_name"),
                    r.get("emergency_contact_phone"),
                    r.get("registration_date"),
                    r.get("gp_surgery_id")
            );
            repo.add(p);
        }
    }
}
