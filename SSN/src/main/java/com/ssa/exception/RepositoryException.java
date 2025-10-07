package com.ssa.exception;

/**
 * Runtime exception for repository/database errors.
 * Wraps checked SQL exceptions as unchecked for cleaner code flow.
 */
public class RepositoryException extends RuntimeException {
    public RepositoryException(String message) {
        super(message);
    }

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
