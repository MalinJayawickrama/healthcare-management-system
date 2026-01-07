package model;

public class Prescription {
    private final String prescriptionId;
    private String patientId;
    private String clinicianId;
    private String medicationName;
    private String dosage;
    private String frequency;
    private String instructions;

    public Prescription(String prescriptionId,
                        String patientId,
                        String clinicianId,
                        String medicationName,
                        String dosage,
                        String frequency,
                        String instructions) {
        this.prescriptionId = prescriptionId;
        this.patientId = patientId;
        this.clinicianId = clinicianId;
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.frequency = frequency;
        this.instructions = instructions;
    }

    public String getPrescriptionId() { return prescriptionId; }
    public String getPatientId() { return patientId; }
    public String getClinicianId() { return clinicianId; }
    public String getMedicationName() { return medicationName; }
    public String getDosage() { return dosage; }
    public String getFrequency() { return frequency; }
    public String getInstructions() { return instructions; }
}
