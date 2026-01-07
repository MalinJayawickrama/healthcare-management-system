package model;

import java.io.FileWriter;
import java.time.LocalDate;

public final class ReferralManager {

    private static ReferralManager instance;

    private final ReferralRepository repo;
    private final ReferralCsvSaver saver;
    private final String referralsCsvPath;
    private final String outDir;

    private ReferralManager(ReferralRepository repo, String referralsCsvPath, String outDir) {
        this.repo = repo;
        this.saver = new ReferralCsvSaver();
        this.referralsCsvPath = referralsCsvPath;
        this.outDir = outDir;
    }

    // Singleton access
    public static ReferralManager getInstance(ReferralRepository repo, String referralsCsvPath, String outDir) {
        if (instance == null) {
            instance = new ReferralManager(repo, referralsCsvPath, outDir);
        }
        return instance;
    }

    public Referral createReferral(String patientId,
                                   String referringClinicianId,
                                   String referredToClinicianId,
                                   String referringFacilityId,
                                   String referredToFacilityId,
                                   String urgencyLevel,
                                   String referralReason,
                                   String clinicalSummary,
                                   String requestedInvestigations,
                                   String status,
                                   String appointmentId,
                                   String notes) {

        String newId = generateNextReferralId();
        String today = LocalDate.now().toString();

        Referral r = new Referral(
                newId,
                safe(patientId),
                safe(referringClinicianId),
                safe(referredToClinicianId),
                safe(referringFacilityId),
                safe(referredToFacilityId),
                today,                    // referral_date
                safe(urgencyLevel),
                safe(referralReason),
                safe(clinicalSummary),
                safe(requestedInvestigations),
                safe(status),
                safe(appointmentId),
                safe(notes),
                today,                    // created_date
                today                     // last_updated
        );

        repo.add(r);
        saver.append(referralsCsvPath, r);
        writeReferralOutputFile(r);

        return r;
    }

    private String safe(String s) {
        return (s == null) ? "" : s.trim();
    }

    private String generateNextReferralId() {
        String prefix = "R";
        int width = 3; // R001

        int max = 0;
        for (Referral r : repo.getAll()) {
            String id = r.getReferralId();
            if (id == null) continue;

            if (id.startsWith(prefix)) {
                String digits = id.substring(prefix.length()).replaceAll("\\D+", "");
                if (!digits.isEmpty()) {
                    try {
                        int v = Integer.parseInt(digits);
                        if (v > max) max = v;
                    } catch (Exception ignored) {}
                }
            }
        }

        return prefix + String.format("%0" + width + "d", (max + 1));
    }

    private void writeReferralOutputFile(Referral r) {
        String filename = outDir + "/referral_" + r.getReferralId() + ".txt";
        try {
            FileWriter fw = new FileWriter(filename, false);

            fw.write("REFERRAL LETTER" + System.lineSeparator());
            fw.write("Referral ID: " + r.getReferralId() + System.lineSeparator());
            fw.write("Date: " + r.getReferralDate() + System.lineSeparator());
            fw.write(System.lineSeparator());

            fw.write("Patient ID: " + r.getPatientId() + System.lineSeparator());
            fw.write("Referring Clinician ID: " + r.getReferringClinicianId() + System.lineSeparator());
            fw.write("Referred To Clinician ID: " + r.getReferredToClinicianId() + System.lineSeparator());
            fw.write("From Facility ID: " + r.getReferringFacilityId() + System.lineSeparator());
            fw.write("To Facility ID: " + r.getReferredToFacilityId() + System.lineSeparator());
            fw.write(System.lineSeparator());

            fw.write("Urgency: " + r.getUrgencyLevel() + System.lineSeparator());
            fw.write("Reason: " + r.getReferralReason() + System.lineSeparator());
            fw.write(System.lineSeparator());

            fw.write("Clinical Summary:" + System.lineSeparator());
            fw.write(r.getClinicalSummary() + System.lineSeparator());
            fw.write(System.lineSeparator());

            fw.write("Requested Investigations:" + System.lineSeparator());
            fw.write(r.getRequestedInvestigations() + System.lineSeparator());
            fw.write(System.lineSeparator());

            fw.write("Notes: " + r.getNotes() + System.lineSeparator());
            fw.write("Status: " + r.getStatus() + System.lineSeparator());
            fw.write("Appointment ID: " + r.getAppointmentId() + System.lineSeparator());

            fw.close();
        } catch (Exception e) {
            System.out.println("Failed to write referral output file: " + e.getMessage());
        }
    }
}
