package org.example.task12.customException;

public class ClassNotAnnotatedException extends RuntimeException{
    public ClassNotAnnotatedException() {
    }

    public ClassNotAnnotatedException(String message) {
        super(message);
    }

    public ClassNotAnnotatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClassNotAnnotatedException(Throwable cause) {
        super(cause);
    }

    public ClassNotAnnotatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
