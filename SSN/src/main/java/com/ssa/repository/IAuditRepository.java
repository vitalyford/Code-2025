package com.ssa.repository;

import com.ssa.model.AuditLog;

import java.time.LocalDateTime;
import java.util.List;

/**
 * BLACK BOX INTERFACE: Repository for AuditLog entity data access.
 * 
 * What this interface does: Provides audit log operations.
 * 
 * Hidden implementation:
 * - SQL queries for audit_logs table
 * - Time-based indexing
 * - Log rotation strategies
 * 
 * Replaceable by: Any implementation (H2, PostgreSQL, time-series DB, logging service)
 */
public interface IAuditRepository extends IRepository<AuditLog, Long> {
    /**
     * Find audit logs within a date range.
     * @param from Start date/time
     * @param to End date/time
     * @return List of audit logs in date range
     */
    List<AuditLog> findByDateRange(LocalDateTime from, LocalDateTime to);

    /**
     * Find audit logs by user.
     * @param userName Username to search for
     * @return List of audit logs for that user
     */
    List<AuditLog> findByUser(String userName);

    /**
     * Find audit logs by action type.
     * @param action Action type (e.g., "SSN_LOOKUP", "APPLICATION_APPROVED")
     * @return List of audit logs for that action
     */
    List<AuditLog> findByAction(String action);

    /**
     * Get recent audit logs.
     * @param limit Maximum number of logs to return
     * @return List of most recent audit logs
     */
    List<AuditLog> findRecent(int limit);
}
