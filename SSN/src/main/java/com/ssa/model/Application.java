package com.ssa.model;

import com.ssa.model.enums.ApplicationStatus;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * PRIMITIVE: Application entity representing an SSN application request.
 * 
 * This is a core primitive that flows through the application workflow.
 * Represents the lifecycle of an SSN application from submission to approval/rejection.
 * 
 * Design Principle: Tracks workflow state and audit information.
 * Links Person to SSN through the application process.
 */
public class Application {
    private Long applicationId;
    private String referenceNumber;  // User-friendly reference (e.g., "APP-2024-001234")
    private Long personId;
    private Person person;  // Associated person object
    private LocalDateTime applicationDate;
    private ApplicationStatus status;
    private LocalDateTime reviewDate;
    private String reviewNotes;
    private String reviewedBy;  // Admin username who processed the application
    private String assignedSSN;  // SSN assigned upon approval

    // Constructor
    public Application() {
        this.applicationDate = LocalDateTime.now();
        this.status = ApplicationStatus.PENDING;
        this.referenceNumber = generateReferenceNumber();
    }

    public Application(Person person) {
        this();
        this.person = person;
        this.personId = person.getPersonId();
    }

    // Business logic methods
    private String generateReferenceNumber() {
        // Format: APP-YYYY-XXXXXX (APP-year-6 digit random)
        int year = LocalDateTime.now().getYear();
        String random = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        return "APP-" + year + "-" + random;
    }

    public boolean isPending() {
        return status == ApplicationStatus.PENDING;
    }

    public boolean isApproved() {
        return status == ApplicationStatus.APPROVED;
    }

    public boolean isRejected() {
        return status == ApplicationStatus.REJECTED;
    }

    public void approve(String adminUser, String ssn) {
        this.status = ApplicationStatus.APPROVED;
        this.reviewedBy = adminUser;
        this.reviewDate = LocalDateTime.now();
        this.assignedSSN = ssn;
    }

    public void reject(String adminUser, String reason) {
        this.status = ApplicationStatus.REJECTED;
        this.reviewedBy = adminUser;
        this.reviewDate = LocalDateTime.now();
        this.reviewNotes = reason;
    }

    // Getters and Setters
    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
        if (person != null) {
            this.personId = person.getPersonId();
        }
    }

    public LocalDateTime getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDateTime applicationDate) {
        this.applicationDate = applicationDate;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public LocalDateTime getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDateTime reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getReviewNotes() {
        return reviewNotes;
    }

    public void setReviewNotes(String reviewNotes) {
        this.reviewNotes = reviewNotes;
    }

    public String getReviewedBy() {
        return reviewedBy;
    }

    public void setReviewedBy(String reviewedBy) {
        this.reviewedBy = reviewedBy;
    }

    public String getAssignedSSN() {
        return assignedSSN;
    }

    public void setAssignedSSN(String assignedSSN) {
        this.assignedSSN = assignedSSN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Application that = (Application) o;
        return Objects.equals(applicationId, that.applicationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationId);
    }

    @Override
    public String toString() {
        return "Application{" +
                "applicationId=" + applicationId +
                ", referenceNumber='" + referenceNumber + '\'' +
                ", status=" + status +
                ", applicationDate=" + applicationDate +
                ", personName='" + (person != null ? person.getFullName() : "Unknown") + '\'' +
                '}';
    }
}
