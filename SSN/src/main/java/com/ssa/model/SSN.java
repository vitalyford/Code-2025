package com.ssa.model;

import com.ssa.model.enums.SSNStatus;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * PRIMITIVE: SSN (Social Security Number) entity.
 * 
 * This is a core primitive representing a unique 9-digit identifier.
 * Format: XXX-XX-XXXX (Area-Group-Serial)
 * 
 * Design Principle: Simple, immutable identifier with validation.
 * The SSN itself is the key - this object wraps it with metadata.
 */
public class SSN {
    private String ssn;  // Format: XXX-XX-XXXX
    private Long personId;
    private LocalDateTime issuedDate;
    private SSNStatus status;

    // Constructor
    public SSN() {
        this.issuedDate = LocalDateTime.now();
        this.status = SSNStatus.ACTIVE;
    }

    public SSN(String ssn, Long personId) {
        this();
        this.ssn = ssn;
        this.personId = personId;
    }

    // Validation methods
    public static boolean isValidFormat(String ssn) {
        if (ssn == null) return false;
        // Remove dashes for validation
        String cleaned = ssn.replace("-", "");
        // Must be exactly 9 digits
        return cleaned.matches("^\\d{9}$");
    }

    public static String formatSSN(String ssn) {
        if (ssn == null) return null;
        String cleaned = ssn.replace("-", "");
        if (cleaned.length() != 9) return ssn;
        return cleaned.substring(0, 3) + "-" + 
               cleaned.substring(3, 5) + "-" + 
               cleaned.substring(5);
    }

    public static String maskSSN(String ssn) {
        if (ssn == null || ssn.length() < 4) return "***-**-****";
        String formatted = formatSSN(ssn);
        return "***-**-" + formatted.substring(7);
    }

    public boolean isActive() {
        return status == SSNStatus.ACTIVE;
    }

    public String getAreaNumber() {
        if (ssn == null || ssn.length() < 3) return null;
        return ssn.substring(0, 3);
    }

    public String getGroupNumber() {
        if (ssn == null || ssn.length() < 6) return null;
        String cleaned = ssn.replace("-", "");
        return cleaned.substring(3, 5);
    }

    public String getSerialNumber() {
        if (ssn == null) return null;
        String cleaned = ssn.replace("-", "");
        if (cleaned.length() < 9) return null;
        return cleaned.substring(5);
    }

    // Getters and Setters
    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public LocalDateTime getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(LocalDateTime issuedDate) {
        this.issuedDate = issuedDate;
    }

    public SSNStatus getStatus() {
        return status;
    }

    public void setStatus(SSNStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SSN ssnObj = (SSN) o;
        return Objects.equals(ssn, ssnObj.ssn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ssn);
    }

    @Override
    public String toString() {
        return "SSN{" +
                "ssn='" + maskSSN(ssn) + '\'' +
                ", status=" + status +
                ", issuedDate=" + issuedDate +
                '}';
    }
}
