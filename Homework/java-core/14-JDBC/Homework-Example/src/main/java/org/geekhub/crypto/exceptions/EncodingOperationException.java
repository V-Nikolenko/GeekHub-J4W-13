package org.geekhub.crypto.exceptions;

public class EncodingOperationException extends RuntimeException {

    public EncodingOperationException(String message) {
        super(message);
    }

    public EncodingOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
