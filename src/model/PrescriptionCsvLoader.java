package model;

import util.CsvReader;
import java.util.List;
import java.util.Map;

public class PrescriptionCsvLoader {

    public void load(String filename, PrescriptionRepository repo) {
        List<Map<String, String>> rows = CsvReader.readAsMaps(filename);

        for (Map<String, String> r : rows) {
            Prescription p = new Prescription(
                    r.get("prescription_id"),
                    r.get("patient_id"),
                    r.get("clinician_id"),
                    r.get("medication_name"),   
                    r.get("dosage"),
                    r.get("frequency"),
                    r.get("instructions")       
            );
            repo.add(p);
        }
    }
}
