package model;

import java.util.List;
import java.util.Map;
import util.CsvReader;

public class PatientCsvLoader {

    // Reads patients.csv and fills the repository
    public void load(String filename, PatientRepository repo) {
        var rows = util.CsvReader.readAsMaps(filename);

        for (var r : rows) {
            String id = r.get("patient_id");
            String first = r.get("first_name");
            String last = r.get("last_name");
            String dob = r.get("date_of_birth");
            String phone = r.get("phone_number");
            String email = r.get("email");
            String address = r.get("address");
            String gp = r.get("gp_surgery_id");

            Patient p = new Patient(
                    id,
                    first,
                    last,
                    dob,
                    phone,
                    email,
                    address,
                    gp
            );

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
