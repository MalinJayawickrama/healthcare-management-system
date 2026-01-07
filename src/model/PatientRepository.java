package model;

import java.util.ArrayList;
import java.util.List;

public class PatientRepository {

    private final List<Patient> patients = new ArrayList<>();

    public List<Patient> getAll() {
        // Return the list (simple for now). Later we can return a copy if needed.
        return patients;
    }

    public int size() {
        return patients.size();
    }

    public void add(Patient patient) {
        patients.add(patient);
    }

    public boolean removeById(String patientId) {
        Patient p = findById(patientId);
        if (p == null) return false;
        return patients.remove(p);
    }

    public Patient findById(String patientId) {
        for (Patient p : patients) {
            if (p.getPatientId().equals(patientId)) return p;
        }
        return null;
    }
}
