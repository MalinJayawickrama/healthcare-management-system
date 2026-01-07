package controller;

import model.PatientCsvLoader;
import model.PatientRepository;
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
}
