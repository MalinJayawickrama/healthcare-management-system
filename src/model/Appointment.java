package model;

public class Appointment {
    private final String appointmentId;
    private String patientId;
    private String clinicianId;
    private String facilityId;
    private String appointmentDate;
    private String appointmentTime;
    private String status;
    private String notes;

    public Appointment(String appointmentId,
                       String patientId,
                       String clinicianId,
                       String facilityId,
                       String appointmentDate,
                       String appointmentTime,
                       String status,
                       String notes) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.clinicianId = clinicianId;
        this.facilityId = facilityId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
        this.notes = notes;
    }

    public String getAppointmentId() { return appointmentId; }
    public String getPatientId() { return patientId; }
    public String getClinicianId() { return clinicianId; }
    public String getFacilityId() { return facilityId; }
    public String getAppointmentDate() { return appointmentDate; }
    public String getAppointmentTime() { return appointmentTime; }
    public String getStatus() { return status; }
    public String getNotes() { return notes; }

    public void setPatientId(String patientId) { this.patientId = patientId; }
    public void setClinicianId(String clinicianId) { this.clinicianId = clinicianId; }
    public void setFacilityId(String facilityId) { this.facilityId = facilityId; }
    public void setAppointmentDate(String appointmentDate) { this.appointmentDate = appointmentDate; }
    public void setAppointmentTime(String appointmentTime) { this.appointmentTime = appointmentTime; }
    public void setStatus(String status) { this.status = status; }
    public void setNotes(String notes) { this.notes = notes; }
}
