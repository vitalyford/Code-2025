package com.ssa.service;

import com.ssa.exception.ApplicationException;
import com.ssa.model.Application;
import com.ssa.model.Person;
import com.ssa.model.enums.ApplicationStatus;

import java.util.List;

/**
 * BLACK BOX INTERFACE: Application Service for SSN application workflow.
 * 
 * What this interface does: Manages SSN application lifecycle.
 * 
 * Hidden implementation:
 * - Application workflow state machine
 * - Business rules for approval/rejection
 * - Integration with SSN generation
 * 
 * Replaceable by: Different workflow rules, approval processes
 */
public interface IApplicationService {
    /**
     * Submit a new SSN application.
     * @param person Person applying for SSN
     * @return Created application with reference number
     * @throws ApplicationException if person already has SSN or application
     */
    Application submitApplication(Person person) throws ApplicationException;

    /**
     * Approve an application and generate SSN.
     * @param applicationId Application ID to approve
     * @param adminUser Admin username processing approval
     * @return Updated application with assigned SSN
     * @throws ApplicationException if application not found or not pending
     */
    Application approveApplication(Long applicationId, String adminUser) throws ApplicationException;

    /**
     * Reject an application.
     * @param applicationId Application ID to reject
     * @param reason Reason for rejection
     * @param adminUser Admin username processing rejection
     * @return Updated application
     * @throws ApplicationException if application not found or not pending
     */
    Application rejectApplication(Long applicationId, String reason, String adminUser) throws ApplicationException;

    /**
     * Get all pending applications.
     * @return List of applications awaiting review
     */
    List<Application> getPendingApplications();

    /**
     * Get application by reference number.
     * @param referenceNumber Application reference
     * @return Application if found
     * @throws ApplicationException if not found
     */
    Application getApplicationByReference(String referenceNumber) throws ApplicationException;

    /**
     * Get all applications for a person.
     * @param personId Person ID
     * @return List of applications for that person
     */
    List<Application> getApplicationsByPerson(Long personId);

    /**
     * Get count of applications by status.
     * @param status Application status
     * @return Count of applications with that status
     */
    long getCountByStatus(ApplicationStatus status);
}
