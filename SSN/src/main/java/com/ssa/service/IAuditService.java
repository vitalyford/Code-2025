package com.ssa.service;

import com.ssa.model.AuditLog;

import java.time.LocalDateTime;
import java.util.List;

/**
 * BLACK BOX INTERFACE: Audit Service for logging operations.
 * 
 * What this interface does: Records and retrieves audit logs.
 * 
 * Hidden implementation:
 * - Logging strategy
 * - Storage mechanism
 * - Log rotation
 * 
 * Replaceable by: Different logging systems, external audit services
 */
public interface IAuditService {
    /**
     * Log an access or operation.
     * @param userName User performing action
     * @param action Action type
     * @param details Action details
     */
    void logAccess(String userName, String action, String details);

    /**
     * Log with IP address.
     * @param userName User performing action
     * @param action Action type
     * @param details Action details
     * @param ipAddress IP address of user
     */
    void logAccess(String userName, String action, String details, String ipAddress);

    /**
     * Get audit logs within date range.
     * @param from Start date/time
     * @param to End date/time
     * @return List of audit logs
     */
    List<AuditLog> getAuditLogs(LocalDateTime from, LocalDateTime to);

    /**
     * Get audit logs for a specific user.
     * @param userName Username to search
     * @return List of audit logs for that user
     */
    List<AuditLog> getLogsByUser(String userName);

    /**
     * Get recent audit logs.
     * @param limit Maximum number of logs
     * @return List of most recent logs
     */
    List<AuditLog> getRecentLogs(int limit);
}
