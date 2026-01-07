package model;

import java.util.ArrayList;
import java.util.List;

public class ClinicianRepository {
    private final List<Clinician> clinicians = new ArrayList<>();

    public List<Clinician> getAll() { return clinicians; }

    public int size() { return clinicians.size(); }

    public void add(Clinician c) { clinicians.add(c); }

    public Clinician findById(String id) {
        for (Clinician c : clinicians) {
            if (c.getClinicianId().equals(id)) return c;
        }
        return null;
    }
}
