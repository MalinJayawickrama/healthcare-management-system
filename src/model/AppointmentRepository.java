package model;

import java.util.ArrayList;
import java.util.List;

public class AppointmentRepository {
    private final List<Appointment> appointments = new ArrayList<>();

    public List<Appointment> getAll() { return appointments; }
    public int size() { return appointments.size(); }

    public void add(Appointment a) { appointments.add(a); }

    public Appointment findById(String id) {
        for (Appointment a : appointments) {
            if (a.getAppointmentId().equals(id)) return a;
        }
        return null;
    }

    public boolean removeById(String id) {
        Appointment a = findById(id);
        if (a == null) return false;
        return appointments.remove(a);
    }
}
