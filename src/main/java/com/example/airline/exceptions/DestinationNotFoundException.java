package com.example.airline.exceptions;


public class DestinationNotFoundException extends RuntimeException {
    public DestinationNotFoundException(Long id) {
        super("Destination id not found: " +  id);
    }
}
