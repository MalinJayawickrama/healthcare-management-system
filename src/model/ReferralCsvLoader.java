package model;

import util.CsvReader;
import java.util.List;
import java.util.Map;

public class ReferralCsvLoader {
    public void load(String filename, ReferralRepository repo) {
        List<Map<String, String>> rows = CsvReader.readAsMaps(filename);

        for (Map<String, String> r : rows) {
            repo.add(new Referral(
                    r.get("referral_id"),
                    r.get("patient_id"),
                    r.get("referring_clinician_id"),
                    r.get("referred_to_clinician_id"),
                    r.get("referring_facility_id"),
                    r.get("referred_to_facility_id"),
                    r.get("referral_date"),
                    r.get("urgency_level"),
                    r.get("referral_reason"),
                    r.get("clinical_summary"),
                    r.get("requested_investigations"),
                    r.get("status"),
                    r.get("appointment_id"),
                    r.get("notes"),
                    r.get("created_date"),
                    r.get("last_updated")
            ));
        }
    }
}
