package com.ssa.repository;

import com.ssa.model.Application;
import com.ssa.model.enums.ApplicationStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * BLACK BOX INTERFACE: Repository for Application entity data access.
 * 
 * What this interface does: Provides Application-specific data operations.
 * 
 * Hidden implementation:
 * - SQL queries for applications table
 * - Join operations with persons table
 * - Date range filtering
 * 
 * Replaceable by: Any implementation (H2, PostgreSQL, MongoDB, etc.)
 */
public interface IApplicationRepository extends IRepository<Application, Long> {
    /**
     * Find application by reference number.
     * @param referenceNumber Application reference (e.g., "APP-2024-ABC123")
     * @return Optional containing application if found
     */
    Optional<Application> findByReferenceNumber(String referenceNumber);

    /**
     * Find all applications with a specific status.
     * @param status Application status
     * @return List of applications with that status
     */
    List<Application> findByStatus(ApplicationStatus status);

    /**
     * Find applications submitted within a date range.
     * @param from Start date/time
     * @param to End date/time
     * @return List of applications in date range
     */
    List<Application> findByDateRange(LocalDateTime from, LocalDateTime to);

    /**
     * Find applications for a specific person.
     * @param personId Person ID
     * @return List of applications for that person
     */
    List<Application> findByPersonId(Long personId);

    /**
     * Count applications by status.
     * @param status Application status
     * @return Count of applications with that status
     */
    long countByStatus(ApplicationStatus status);
}
