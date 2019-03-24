package io.vinson.file.converter.exception;

public class OfficeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public OfficeException(String message) {
        super(message);
    }

    public OfficeException(String message, Throwable cause) {
        super(message, cause);
    }
}
