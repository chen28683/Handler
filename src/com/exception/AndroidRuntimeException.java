package com.exception;
/**
 * Base class for all unchecked exceptions thrown by the Android frameworks.
 */
public class AndroidRuntimeException extends RuntimeException {
    public AndroidRuntimeException() {
    }

    public AndroidRuntimeException(String name) {
        super(name);
    }

    public AndroidRuntimeException(String name, Throwable cause) {
        super(name, cause);
    }

    public AndroidRuntimeException(Exception cause) {
        super(cause);
    }
};