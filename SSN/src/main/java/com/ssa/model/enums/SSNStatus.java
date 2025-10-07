package com.ssa.model.enums;

/**
 * Status of a Social Security Number.
 * Tracks the lifecycle state of an SSN.
 */
public enum SSNStatus {
    ACTIVE("Active"),
    SUSPENDED("Suspended"),
    REVOKED("Revoked");

    private final String displayName;

    SSNStatus(String displayName) {
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
