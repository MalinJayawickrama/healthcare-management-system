package model;

import java.io.FileWriter;

public class PatientCsvSaver {

    public void append(String filename, Patient p) {
        try {
            FileWriter fw = new FileWriter(filename, true);

            // Ensure new row starts on a new line (safe even if file already ends with newline)
            fw.write(System.lineSeparator());

            // Write in EXACT header order:
            // patient_id,first_name,last_name,date_of_birth,nhs_number,gender,phone_number,email,address,postcode,
            // emergency_contact_name,emergency_contact_phone,registration_date,gp_surgery_id
            fw.write(csv(p.getPatientId()) + "," +
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
                     csv(p.getGpSurgeryId()));

            fw.close();
        } catch (Exception e) {
            System.out.println("Failed to append patient: " + e.getMessage());
        }
    }

    // Minimal CSV escaping: wrap in quotes if contains comma or quote; double quotes inside.
    private String csv(String s) {
        if (s == null) return "";
        if (s.contains(",") || s.contains("\"")) {
            s = s.replace("\"", "\"\"");
            return "\"" + s + "\"";
        }
        return s;
    }
}
