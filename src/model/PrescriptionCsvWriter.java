package model;

import java.io.FileWriter;
import java.util.List;

public class PrescriptionCsvWriter {

    public void writeAll(String filename, List<Prescription> prescriptions) {
        try {
            FileWriter fw = new FileWriter(filename, false);

            fw.write("prescription_id,patient_id,clinician_id,medication_name,dosage,frequency,instructions");

            for (Prescription p : prescriptions) {
                fw.write(System.lineSeparator());
                fw.write(
                        csv(p.getPrescriptionId()) + "," +
                        csv(p.getPatientId()) + "," +
                        csv(p.getClinicianId()) + "," +
                        csv(p.getMedicationName()) + "," +
                        csv(p.getDosage()) + "," +
                        csv(p.getFrequency()) + "," +
                        csv(p.getInstructions())
                );
            }

            fw.close();
        } catch (Exception e) {
            System.out.println("Failed to write prescriptions file: " + e.getMessage());
        }
    }

    private String csv(String s) {
        if (s == null) return "";
        if (s.contains(",") || s.contains("\"")) {
            s = s.replace("\"", "\"\"");
            return "\"" + s + "\"";
        }
        return s;
    }
}
