package com.ssa.exception;

/**
 * Exception thrown when an SSN application operation fails.
 */
public class ApplicationException extends Exception {
    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
