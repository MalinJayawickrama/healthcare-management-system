package model;

import java.io.FileWriter;
import java.util.List;

public class ClinicianCsvWriter {

    public void writeAll(String filename, List<Clinician> clinicians) {
        try {
            FileWriter fw = new FileWriter(filename, false);

            fw.write("clinician_id,first_name,last_name,title,speciality,gmc_number,phone_number,email,workplace_id,workplace_type,employment_status,start_date");

            for (Clinician c : clinicians) {
                fw.write(System.lineSeparator());
                fw.write(
                        csv(c.getClinicianId()) + "," +
                        csv(c.getFirstName()) + "," +
                        csv(c.getLastName()) + "," +
                        csv(c.getTitle()) + "," +
                        csv(c.getSpeciality()) + "," +
                        csv(c.getGmcNumber()) + "," +
                        csv(c.getPhoneNumber()) + "," +
                        csv(c.getEmail()) + "," +
                        csv(c.getWorkplaceId()) + "," +
                        csv(c.getWorkplaceType()) + "," +
                        csv(c.getEmploymentStatus()) + "," +
                        csv(c.getStartDate())
                );
            }

            fw.close();
        } catch (Exception e) {
            System.out.println("Failed to write clinicians file: " + e.getMessage());
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
