package com.ssa.model.enums;

/**
 * Citizenship status types for SSN applicants.
 * Determines eligibility for SSN issuance.
 */
public enum CitizenshipStatus {
    US_CITIZEN("U.S. Citizen"),
    PERMANENT_RESIDENT("Lawful Permanent Resident"),
    WORK_VISA("Work Visa Holder"),
    OTHER_AUTHORIZED("Other Authorized Status");

    private final String displayName;

    CitizenshipStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
