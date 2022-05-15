package org.example.task12.customException;

public class FieldNotAnnotatedException extends RuntimeException {

    public FieldNotAnnotatedException() {
    }

    public FieldNotAnnotatedException(String message) {
        super(message);
    }

    public FieldNotAnnotatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public FieldNotAnnotatedException(Throwable cause) {
        super(cause);
    }

    public FieldNotAnnotatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
