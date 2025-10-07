package com.ssa.exception;

/**
 * Base exception for SSN-related errors.
 * All SSN-specific exceptions extend this class.
 */
public class SSNException extends Exception {
    public SSNException(String message) {
        super(message);
    }

    public SSNException(String message, Throwable cause) {
        super(message, cause);
    }
}
