package model;

import java.io.FileWriter;

public class ReferralCsvSaver {

    public void append(String filename, Referral r) {
        try {
            FileWriter fw = new FileWriter(filename, true);
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
            fw.close();
        } catch (Exception e) {
            System.out.println("Failed to append referral: " + e.getMessage());
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
