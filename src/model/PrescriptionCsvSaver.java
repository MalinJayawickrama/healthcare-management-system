package model;

import java.io.FileWriter;

public class PrescriptionCsvSaver {

    public void append(String filename, Prescription p) {
        try {
            FileWriter fw = new FileWriter(filename, true);

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

            fw.close();
        } catch (Exception e) {
            System.out.println("Failed to append prescription: " + e.getMessage());
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
