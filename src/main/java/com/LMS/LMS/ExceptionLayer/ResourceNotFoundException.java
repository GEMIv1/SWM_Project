package com.LMS.LMS.ExceptionLayer;

public class ResourceNotFoundException extends LMSException  {
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
