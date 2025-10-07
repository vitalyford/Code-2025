package com.ssa.repository;

import com.ssa.model.SSN;
import com.ssa.model.enums.SSNStatus;

import java.util.List;
import java.util.Optional;

/**
 * BLACK BOX INTERFACE: Repository for SSN entity data access.
 * 
 * What this interface does: Provides SSN-specific data operations.
 * 
 * Hidden implementation:
 * - SQL queries for ssns table
 * - SSN uniqueness enforcement
 * - Status filtering
 * 
 * Replaceable by: Any implementation (H2, PostgreSQL, MongoDB, etc.)
 */
public interface ISSNRepository extends IRepository<SSN, String> {
    /**
     * Find SSN by the SSN string itself.
     * @param ssn SSN string (with or without dashes)
     * @return Optional containing SSN if found
     */
    Optional<SSN> findBySSN(String ssn);

    /**
     * Find SSN associated with a person.
     * @param personId Person ID
     * @return Optional containing SSN if person has one
     */
    Optional<SSN> findByPersonId(Long personId);

    /**
     * Check if SSN already exists.
     * @param ssn SSN string to check
     * @return true if SSN exists
     */
    boolean ssnExists(String ssn);

    /**
     * Find all SSNs with a specific status.
     * @param status SSN status
     * @return List of SSNs with that status
     */
    List<SSN> findByStatus(SSNStatus status);

    /**
     * Get the latest SSN issued (for sequence tracking).
     * @return Optional containing the most recently issued SSN
     */
    Optional<SSN> getLatestSSN();
}
