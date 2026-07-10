package com.resume.portal.exception;

public class GlobalExceptionHandler {
    
    public void handleResourceNotFoundException(ResourceNotFoundException ex) {
        System.err.println("Resource Not Found: " + ex.getMessage());
    }

    public void handleGenericException(Exception ex) {
        System.err.println("An error occurred: " + ex.getMessage());
        ex.printStackTrace();
    }
}
