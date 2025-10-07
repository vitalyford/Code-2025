package com.ssa.service;

import com.ssa.exception.ValidationException;
import com.ssa.model.Person;

import java.util.List;

/**
 * BLACK BOX INTERFACE: Person Service for person management.
 * 
 * What this interface does: Manages person records.
 * 
 * Hidden implementation:
 * - Validation logic
 * - Duplicate checking
 * - Data consistency rules
 * 
 * Replaceable by: Different validation rules, data management strategies
 */
public interface IPersonService {
    /**
     * Create a new person record.
     * @param person Person to create
     * @return Created person with generated ID
     * @throws ValidationException if person data is invalid
     */
    Person createPerson(Person person) throws ValidationException;

    /**
     * Update an existing person record.
     * @param person Person with updated data
     * @return Updated person
     * @throws ValidationException if person data is invalid or not found
     */
    Person updatePerson(Person person) throws ValidationException;

    /**
     * Find person by ID.
     * @param personId Person ID
     * @return Person if found
     * @throws ValidationException if not found
     */
    Person findById(Long personId) throws ValidationException;

    /**
     * Find persons by name.
     * @param firstName First name
     * @param lastName Last name
     * @return List of matching persons
     */
    List<Person> findByName(String firstName, String lastName);

    /**
     * Find person by SSN.
     * @param ssn Social Security Number
     * @return Person if found
     * @throws ValidationException if not found
     */
    Person findBySSN(String ssn) throws ValidationException;

    /**
     * Check if person already has an SSN.
     * @param personId Person ID
     * @return true if person has SSN assigned
     */
    boolean hasSSN(Long personId);

    /**
     * Get all persons.
     * @return List of all persons in system
     */
    List<Person> getAllPersons();
}
