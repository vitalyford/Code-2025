package com.ssa.model;

import com.ssa.model.enums.CitizenshipStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * PRIMITIVE: Person entity representing an individual in the system.
 * 
 * This is a core primitive data type that flows through the entire system.
 * Represents demographic and identity information for SSN applicants.
 * 
 * Design Principle: Simple data holder with validation logic.
 * Can be used independently of other system components.
 */
public class Person {
    private Long personId;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String placeOfBirth;
    private String mothersMaidenName;
    private String fathersName;
    private CitizenshipStatus citizenshipStatus;
    private String ssn;  // Optional - only populated after SSN issuance
    private LocalDateTime createdDate;

    // Constructor
    public Person() {
        this.createdDate = LocalDateTime.now();
    }

    public Person(String firstName, String lastName, LocalDate dateOfBirth, 
                  String placeOfBirth, String mothersMaidenName, String fathersName,
                  CitizenshipStatus citizenshipStatus) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.placeOfBirth = placeOfBirth;
        this.mothersMaidenName = mothersMaidenName;
        this.fathersName = fathersName;
        this.citizenshipStatus = citizenshipStatus;
    }

    // Validation methods (keep business logic in the primitive)
    public boolean isValid() {
        return firstName != null && !firstName.trim().isEmpty() &&
               lastName != null && !lastName.trim().isEmpty() &&
               dateOfBirth != null &&
               placeOfBirth != null && !placeOfBirth.trim().isEmpty() &&
               mothersMaidenName != null && !mothersMaidenName.trim().isEmpty() &&
               fathersName != null && !fathersName.trim().isEmpty() &&
               citizenshipStatus != null;
    }

    public boolean isEligibleForSSN() {
        // Business rule: Must be born and younger than 120 years
        if (dateOfBirth == null) return false;
        LocalDate now = LocalDate.now();
        return !dateOfBirth.isAfter(now) && 
               dateOfBirth.isAfter(now.minusYears(120));
    }

    public String getFullName() {
        if (middleName != null && !middleName.trim().isEmpty()) {
            return firstName + " " + middleName + " " + lastName;
        }
        return firstName + " " + lastName;
    }

    // Getters and Setters
    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getMothersMaidenName() {
        return mothersMaidenName;
    }

    public void setMothersMaidenName(String mothersMaidenName) {
        this.mothersMaidenName = mothersMaidenName;
    }

    public String getFathersName() {
        return fathersName;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }

    public CitizenshipStatus getCitizenshipStatus() {
        return citizenshipStatus;
    }

    public void setCitizenshipStatus(CitizenshipStatus citizenshipStatus) {
        this.citizenshipStatus = citizenshipStatus;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(personId, person.personId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId);
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", name='" + getFullName() + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", ssn='" + (ssn != null ? "***-**-" + ssn.substring(7) : "Not Assigned") + '\'' +
                '}';
    }
}
