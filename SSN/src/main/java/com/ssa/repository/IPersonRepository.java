package com.ssa.repository;

import com.ssa.model.Person;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * BLACK BOX INTERFACE: Repository for Person entity data access.
 * 
 * What this interface does: Provides Person-specific data operations.
 * 
 * Hidden implementation:
 * - SQL queries for persons table
 * - Join operations with SSN table
 * - Indexing strategies
 * 
 * Replaceable by: Any implementation (H2, PostgreSQL, MongoDB, etc.)
 */
public interface IPersonRepository extends IRepository<Person, Long> {
    /**
     * Find persons by first and last name.
     * @param firstName First name (case-insensitive)
     * @param lastName Last name (case-insensitive)
     * @return List of matching persons
     */
    List<Person> findByName(String firstName, String lastName);

    /**
     * Find person by SSN.
     * @param ssn Social Security Number
     * @return Optional containing person if found
     */
    Optional<Person> findBySSN(String ssn);

    /**
     * Find persons by date of birth.
     * @param dateOfBirth Date of birth
     * @return List of persons born on that date
     */
    List<Person> findByDateOfBirth(LocalDate dateOfBirth);

    /**
     * Check if person already has an SSN.
     * @param personId Person ID
     * @return true if person has SSN assigned
     */
    boolean hasSSN(Long personId);
}
