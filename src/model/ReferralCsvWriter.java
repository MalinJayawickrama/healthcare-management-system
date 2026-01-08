package model;

import java.io.FileWriter;
import java.util.List;

public class ReferralCsvWriter {

    public void writeAll(String filename, List<Referral> referrals) {
        try {
            FileWriter fw = new FileWriter(filename, false);

            fw.write("referral_id,patient_id,referring_clinician_id,referred_to_clinician_id,referring_facility_id,referred_to_facility_id," +
                     "referral_date,urgency_level,referral_reason,clinical_summary,requested_investigations,status,appointment_id,notes,created_date,last_updated");

            for (Referral r : referrals) {
                fw.write(System.lineSeparator());
                fw.write(
                        csv(r.getReferralId()) + "," +
                        csv(r.getPatientId()) + "," +
                        csv(r.getReferringClinicianId()) + "," +
                        csv(r.getReferredToClinicianId()) + "," +
                        csv(r.getReferringFacilityId()) + "," +
                        csv(r.getReferredToFacilityId()) + "," +
                        csv(r.getReferralDate()) + "," +
                        csv(r.getUrgencyLevel()) + "," +
                        csv(r.getReferralReason()) + "," +
                        csv(r.getClinicalSummary()) + "," +
                        csv(r.getRequestedInvestigations()) + "," +
                        csv(r.getStatus()) + "," +
                        csv(r.getAppointmentId()) + "," +
                        csv(r.getNotes()) + "," +
                        csv(r.getCreatedDate()) + "," +
                        csv(r.getLastUpdated())
                );
            }

            fw.close();
        } catch (Exception e) {
            System.out.println("Failed to write referrals file: " + e.getMessage());
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
