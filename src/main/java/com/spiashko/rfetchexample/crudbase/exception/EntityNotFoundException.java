package com.spiashko.rfetchexample.crudbase.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String error) {
        super(error);
    }
}
