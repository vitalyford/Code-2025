package com.ssa.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * PRIMITIVE: AuditLog entity for tracking system operations.
 * 
 * Maintains an immutable record of all sensitive operations in the system.
 * Used for compliance, security monitoring, and troubleshooting.
 * 
 * Design Principle: Simple, append-only log entries.
 */
public class AuditLog {
    private Long logId;
    private LocalDateTime timestamp;
    private String userName;
    private String action;
    private String details;
    private String ipAddress;

    // Constructor
    public AuditLog() {
        this.timestamp = LocalDateTime.now();
    }

    public AuditLog(String userName, String action, String details) {
        this();
        this.userName = userName;
        this.action = action;
        this.details = details;
    }

    public AuditLog(String userName, String action, String details, String ipAddress) {
        this(userName, action, details);
        this.ipAddress = ipAddress;
    }

    // Getters and Setters
    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuditLog auditLog = (AuditLog) o;
        return Objects.equals(logId, auditLog.logId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logId);
    }

    @Override
    public String toString() {
        return "AuditLog{" +
                "timestamp=" + timestamp +
                ", userName='" + userName + '\'' +
                ", action='" + action + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
