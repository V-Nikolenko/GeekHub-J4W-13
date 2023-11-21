package org.geekhub.example.exception;

public class FileSizeException extends RuntimeException {
    public FileSizeException(String message) {
        super(message);
    }
}
