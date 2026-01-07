package model;

import java.io.FileWriter;
import java.util.List;

public class PatientCsvWriter {

    public void writeAll(String filename, List<Patient> patients) {
        try {
            FileWriter fw = new FileWriter(filename, false);

            // header EXACTLY as your CSV
            fw.write(
                "patient_id,first_name,last_name,date_of_birth,nhs_number,gender," +
                "phone_number,email,address,postcode,emergency_contact_name," +
                "emergency_contact_phone,registration_date,gp_surgery_id"
            );

            for (Patient p : patients) {
                fw.write(System.lineSeparator());
                fw.write(
                    csv(p.getPatientId()) + "," +
                    csv(p.getFirstName()) + "," +
                    csv(p.getLastName()) + "," +
                    csv(p.getDateOfBirth()) + "," +
                    csv(p.getNhsNumber()) + "," +
                    csv(p.getGender()) + "," +
                    csv(p.getPhoneNumber()) + "," +
                    csv(p.getEmail()) + "," +
                    csv(p.getAddress()) + "," +
                    csv(p.getPostcode()) + "," +
                    csv(p.getEmergencyContactName()) + "," +
                    csv(p.getEmergencyContactPhone()) + "," +
                    csv(p.getRegistrationDate()) + "," +
                    csv(p.getGpSurgeryId())
                );
            }

            fw.close();

        } catch (Exception e) {
            System.out.println("Failed to write patients file: " + e.getMessage());
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
