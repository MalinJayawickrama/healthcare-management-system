package controller;

import model.PatientCsvLoader;
import model.PatientRepository;
import model.Patient;
import model.PatientCsvSaver;
import view.MainFrame;


public class AppController {

    private final MainFrame mainFrame;

    // Model
    private final PatientRepository patientRepo = new PatientRepository();

    public AppController() {
        this.mainFrame = new MainFrame(this);
    }

    public void start() {
        loadPatients();
        mainFrame.setVisible(true);
    }

    private void loadPatients() {
        PatientCsvLoader loader = new PatientCsvLoader();
        loader.load("data/patients.csv", patientRepo);

        System.out.println("Patients loaded: " + patientRepo.size());

    }


    // Controller API for views (Phase later: tables)
    public PatientRepository getPatientRepository() {
        return patientRepo;
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
        int max = 0;
        for (var p : patientRepo.getAll()) {
            String id = p.getPatientId();
            if (id == null) continue;

            // If IDs are numeric like "12"
            try {
                int v = Integer.parseInt(id.trim());
                if (v > max) max = v;
                continue;
            } catch (Exception ignored) { }

            // If IDs have digits like "P012"
            String digits = id.replaceAll("\\D+", "");
            if (!digits.isEmpty()) {
                try {
                    int v = Integer.parseInt(digits);
                    if (v > max) max = v;
                } catch (Exception ignored) { }
            }
        }
        return String.valueOf(max + 1);
    }

}
