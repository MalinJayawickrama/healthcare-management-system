package model;

import util.CsvReader;
import java.util.List;
import java.util.Map;

public class ClinicianCsvLoader {

    public void load(String filename, ClinicianRepository repo) {
        List<Map<String, String>> rows = CsvReader.readAsMaps(filename);

        for (Map<String, String> r : rows) {
            repo.add(new Clinician(
                    r.get("clinician_id"),
                    r.get("first_name"),
                    r.get("last_name"),
                    r.get("title"),
                    r.get("speciality"),
                    r.get("gmc_number"),
                    r.get("phone_number"),
                    r.get("email"),
                    r.get("workplace_id"),
                    r.get("workplace_type"),
                    r.get("employment_status"),
                    r.get("start_date")
            ));
        }
    }
}
