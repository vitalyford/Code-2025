package com.ssa.repository;

import java.util.List;
import java.util.Optional;

/**
 * BLACK BOX INTERFACE: Generic repository for data access operations.
 * 
 * What this interface does: Defines standard CRUD operations for any entity.
 * 
 * Hidden implementation: 
 * - SQL queries
 * - Connection management
 * - Transaction handling
 * - Database-specific details
 * 
 * Replaceable by: Any data storage implementation (SQL, NoSQL, in-memory, file-based)
 * 
 * @param <T> Entity type
 * @param <ID> ID type
 */
public interface IRepository<T, ID> {
    /**
     * Save or update an entity.
     * @param entity Entity to save
     * @return Saved entity with generated ID
     */
    T save(T entity);

    /**
     * Find entity by ID.
     * @param id Entity ID
     * @return Optional containing entity if found
     */
    Optional<T> findById(ID id);

    /**
     * Find all entities of this type.
     * @return List of all entities
     */
    List<T> findAll();

    /**
     * Delete entity by ID.
     * @param id Entity ID to delete
     */
    void delete(ID id);

    /**
     * Check if entity exists.
     * @param id Entity ID
     * @return true if exists, false otherwise
     */
    boolean exists(ID id);

    /**
     * Count total number of entities.
     * @return Total count
     */
    long count();
}
