package com.ssa.service;

import com.ssa.exception.SSNException;
import com.ssa.model.SSN;

import java.time.LocalDate;
import java.util.List;

/**
 * BLACK BOX INTERFACE: SSN Service for SSN operations.
 * 
 * What this interface does: Manages SSN generation, validation, and lookup.
 * 
 * Hidden implementation:
 * - SSN generation algorithm (area-group-serial)
 * - Validation rules
 * - Business logic for SSN operations
 * 
 * Replaceable by: Different SSN generation strategies, validation rules
 */
public interface ISSNService {
    /**
     * Generate a new unique SSN for a person.
     * @param personId Person ID to associate with SSN
     * @return Generated SSN object
     * @throws SSNException if generation fails or person already has SSN
     */
    SSN generateSSN(Long personId) throws SSNException;

    /**
     * Validate SSN format and existence.
     * @param ssn SSN string to validate
     * @return true if valid format and exists in system
     */
    boolean validateSSN(String ssn);

    /**
     * Look up SSN details.
     * @param ssn SSN string to look up
     * @return SSN object if found
     * @throws SSNException if SSN not found
     */
    SSN lookupSSN(String ssn) throws SSNException;

    /**
     * Search for SSNs by person criteria.
     * @param firstName First name
     * @param lastName Last name
     * @param dateOfBirth Date of birth
     * @return List of matching SSNs
     */
    List<SSN> searchByPerson(String firstName, String lastName, LocalDate dateOfBirth);

    /**
     * Suspend an SSN (mark as inactive).
     * @param ssn SSN to suspend
     * @param reason Reason for suspension
     * @throws SSNException if SSN not found or already suspended
     */
    void suspendSSN(String ssn, String reason) throws SSNException;

    /**
     * Reactivate a suspended SSN.
     * @param ssn SSN to reactivate
     * @throws SSNException if SSN not found or not suspended
     */
    void reactivateSSN(String ssn) throws SSNException;

    /**
     * Get total count of issued SSNs.
     * @return Total number of SSNs in system
     */
    long getTotalSSNCount();
}
