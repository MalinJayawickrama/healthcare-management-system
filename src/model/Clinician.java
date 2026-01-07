package model;

public class Clinician {
    private final String clinicianId;
    private String firstName;
    private String lastName;
    private String role;
    private String phoneNumber;
    private String email;
    private String facilityId;

    public Clinician(String clinicianId,
                     String firstName,
                     String lastName,
                     String role,
                     String phoneNumber,
                     String email,
                     String facilityId) {
        this.clinicianId = clinicianId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.facilityId = facilityId;
    }

    public String getClinicianId() { return clinicianId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getRole() { return role; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }
    public String getFacilityId() { return facilityId; }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setRole(String role) { this.role = role; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setEmail(String email) { this.email = email; }
    public void setFacilityId(String facilityId) { this.facilityId = facilityId; }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return clinicianId + " - " + getFullName();
    }
}
