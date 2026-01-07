package model;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionRepository {
    private final List<Prescription> prescriptions = new ArrayList<>();

    public List<Prescription> getAll() { return prescriptions; }
    public int size() { return prescriptions.size(); }

    public void add(Prescription p) { prescriptions.add(p); }

    public Prescription findById(String id) {
        for (Prescription p : prescriptions) {
            if (p.getPrescriptionId().equals(id)) return p;
        }
        return null;
    }

    public boolean removeById(String id) {
        Prescription p = findById(id);
        if (p == null) return false;
        return prescriptions.remove(p);
    }
}
