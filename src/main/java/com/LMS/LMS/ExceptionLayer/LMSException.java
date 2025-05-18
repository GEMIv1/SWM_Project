package com.LMS.LMS.ExceptionLayer;

public class LMSException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public LMSException(String message) {
        super(message);
    }

    public LMSException(String message, Throwable cause) {
        super(message, cause);
    }
}
