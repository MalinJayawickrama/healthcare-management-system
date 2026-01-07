package model;

import java.io.FileWriter;

public class ClinicianCsvSaver {

    public void append(String filename, Clinician c) {
        try {
            FileWriter fw = new FileWriter(filename, true);
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
            fw.close();
        } catch (Exception e) {
            System.out.println("Failed to append clinician: " + e.getMessage());
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
