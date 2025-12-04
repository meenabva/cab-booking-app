package com.example.cab.booking.exception;

public class NoCabsAvailableException extends RuntimeException {

    public NoCabsAvailableException(String message) {
        super(message);
    }

}
