package model;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionRepository {
    private final List<Prescription> prescriptions = new ArrayList<>();

    public List<Prescription> getAll() { return prescriptions; }
    public int size() { return prescriptions.size(); }

    public void add(Prescription p) { prescriptions.add(p); }
}
