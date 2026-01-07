package controller;

import model.PatientCsvLoader;
import model.PatientRepository;
import model.Patient;
import model.PatientCsvSaver;
import model.PatientCsvWriter;
import model.ClinicianCsvLoader;
import model.ClinicianRepository;
import model.AppointmentCsvLoader;
import model.AppointmentRepository;
import model.PrescriptionCsvLoader;
import model.PrescriptionRepository;
import model.Prescription;
import model.PrescriptionCsvSaver;
import model.ReferralCsvLoader;
import model.ReferralManager;
import model.ReferralRepository;
import model.Referral;
import view.MainFrame;


public class AppController {

    private final MainFrame mainFrame;

    // Model
    private final PatientRepository patientRepo = new PatientRepository();
    private final ClinicianRepository clinicianRepo = new ClinicianRepository();
    private final AppointmentRepository appointmentRepo = new AppointmentRepository();
    private final PrescriptionRepository prescriptionRepo = new PrescriptionRepository();
    private final ReferralRepository referralRepo = new ReferralRepository();

    public AppController() {
        this.mainFrame = new MainFrame(this);
    }

    public void start() {
        loadPatients();
        loadClinicians();
        loadAppointments();
        loadPrescriptions();
        loadReferrals();
        mainFrame.setVisible(true);
    }

    private void loadPatients() {
        PatientCsvLoader loader = new PatientCsvLoader();
        loader.load("data/patients.csv", patientRepo);
        System.out.println("Patients loaded: " + patientRepo.size());

    }
    private void loadClinicians() {
        ClinicianCsvLoader loader = new ClinicianCsvLoader();
        loader.load("data/clinicians.csv", clinicianRepo);
        System.out.println("Clinicians loaded: " + clinicianRepo.size());
    }

    private void loadAppointments() {
        AppointmentCsvLoader loader = new AppointmentCsvLoader();
        loader.load("data/appointments.csv", appointmentRepo);
        System.out.println("Appointments loaded: " + appointmentRepo.size());
    }

    private void loadPrescriptions() {
        PrescriptionCsvLoader loader = new PrescriptionCsvLoader();
        loader.load("data/prescriptions.csv", prescriptionRepo);
        System.out.println("Prescriptions loaded: " + prescriptionRepo.size());
    }

    private void loadReferrals() {
        ReferralCsvLoader loader = new ReferralCsvLoader();
        loader.load("data/referrals.csv", referralRepo);
        System.out.println("Referrals loaded: " + referralRepo.size());
    }

    // Controller API for views 
    public PatientRepository getPatientRepository() {
        return patientRepo;
    }

    public ClinicianRepository getClinicianRepository() {
        return clinicianRepo;
    }
    
    public AppointmentRepository getAppointmentRepository() {
        return appointmentRepo;
    }

    public PrescriptionRepository getPrescriptionRepository() {
        return prescriptionRepo;
    }

    public ReferralRepository getReferralRepository() {
        return referralRepo;
    }

    public Patient addPatient(String firstName,
                          String lastName,
                          String dateOfBirth,
                          String phoneNumber,
                          String email,
                          String address,
                          String postcode,
                          String gpSurgeryId) {

        String newId = generateNextPatientId();

        // For fields we don’t collect in the dialog yet, store blanks safely.
        Patient p = new Patient(
                newId,
                safe(firstName),
                safe(lastName),
                safe(dateOfBirth),
                "",                 // nhs_number
                "",                 // gender
                safe(phoneNumber),
                safe(email),
                safe(address),
                safe(postcode),
                "",                 // emergency_contact_name
                "",                 // emergency_contact_phone
                "",                 // registration_date
                safe(gpSurgeryId)
        );

        patientRepo.add(p);

        PatientCsvSaver saver = new PatientCsvSaver();
        saver.append("data/patients.csv", p);

        return p;
    }

    private String safe(String s) {
        return (s == null) ? "" : s.trim();
    }

    private String generateNextPatientId() {
        String prefix = "P";
        int width = 3; // because P001

        int max = 0;
        for (var p : patientRepo.getAll()) {
            String id = p.getPatientId();
            if (id == null) continue;

            // Match like P001, P010, P123
            var m = java.util.regex.Pattern
                    .compile("^" + java.util.regex.Pattern.quote(prefix) + "(\\d+)$")
                    .matcher(id.trim());

            if (m.matches()) {
                try {
                    int v = Integer.parseInt(m.group(1));
                    if (v > max) max = v;
                } catch (Exception ignored) {}
            }
        }

        int next = max + 1;
        return prefix + String.format("%0" + width + "d", next);  // e.g. P011
    }

    public boolean deletePatientById(String patientId) {
        boolean removed = patientRepo.removeById(patientId);
        if (!removed) return false;

        PatientCsvWriter writer = new PatientCsvWriter();
        writer.writeAll("data/patients.csv", patientRepo.getAll());
        return true;
    }

    public boolean updatePatient(Patient updated) {
        Patient existing = patientRepo.findById(updated.getPatientId());
        if (existing == null) return false;

        existing.setFirstName(updated.getFirstName());
        existing.setLastName(updated.getLastName());
        existing.setDateOfBirth(updated.getDateOfBirth());
        existing.setNhsNumber(updated.getNhsNumber());
        existing.setGender(updated.getGender());
        existing.setPhoneNumber(updated.getPhoneNumber());
        existing.setEmail(updated.getEmail());
        existing.setAddress(updated.getAddress());
        existing.setPostcode(updated.getPostcode());
        existing.setEmergencyContactName(updated.getEmergencyContactName());
        existing.setEmergencyContactPhone(updated.getEmergencyContactPhone());
        existing.setRegistrationDate(updated.getRegistrationDate());
        existing.setGpSurgeryId(updated.getGpSurgeryId());

        // Persist full file rewrite
        PatientCsvWriter writer = new PatientCsvWriter();
        writer.writeAll("data/patients.csv", patientRepo.getAll());
        return true;
    }
    public Prescription addPrescription(String patientId,
                                        String clinicianId,
                                        String medicationName,
                                        String dosage,
                                        String frequency,
                                        String instructions) {

        String newId = generateNextPrescriptionId();

        Prescription p = new Prescription(
                newId,
                patientId.trim(),
                clinicianId.trim(),
                medicationName.trim(),
                dosage.trim(),
                frequency.trim(),
                instructions.trim()
        );

        prescriptionRepo.add(p);

        PrescriptionCsvSaver saver = new PrescriptionCsvSaver();
        saver.append("data/prescriptions.csv", p);

        return p;
    }
    private String generateNextPrescriptionId() {
        String prefix = "RX";
        int width = 3; // RX001

        int max = 0;
        for (var p : prescriptionRepo.getAll()) {
            String id = p.getPrescriptionId();
            if (id == null) continue;

            var m = java.util.regex.Pattern
                    .compile("^" + prefix + "(\\d+)$")
                    .matcher(id.trim());

            if (m.matches()) {
                try {
                    int v = Integer.parseInt(m.group(1));
                    if (v > max) max = v;
                } catch (Exception ignored) {}
            }
        }

        int next = max + 1;
        return prefix + String.format("%0" + width + "d", next);
    }
    public Referral addReferral(String patientId,
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

        ReferralManager manager = ReferralManager.getInstance(referralRepo, "data/referrals.csv", "out");
        return manager.createReferral(
                patientId,
                referringClinicianId,
                referredToClinicianId,
                referringFacilityId,
                referredToFacilityId,
                urgencyLevel,
                referralReason,
                clinicalSummary,
                requestedInvestigations,
                status,
                appointmentId,
                notes
        );
    }
}
