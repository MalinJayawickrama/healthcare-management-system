package model;

public class Patient {
    private final String patientId;
    private String firstName;
    private String lastName;

    // Keep these as Strings for now (CSV-safe). We'll refine later if needed.
    private String dateOfBirth;
    private String phone;
    private String email;
    private String address;
    private String gpSurgeryId;

    public Patient(String patientId,
                   String firstName,
                   String lastName,
                   String dateOfBirth,
                   String phone,
                   String email,
                   String address,
                   String gpSurgeryId) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.gpSurgeryId = gpSurgeryId;
    }

    public String getPatientId() { return patientId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getDateOfBirth() { return dateOfBirth; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getGpSurgeryId() { return gpSurgeryId; }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setEmail(String email) { this.email = email; }
    public void setAddress(String address) { this.address = address; }
    public void setGpSurgeryId(String gpSurgeryId) { this.gpSurgeryId = gpSurgeryId; }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return patientId + " - " + getFullName();
    }
}
