package com.app.prices.domain.exception;

public abstract class GeneralException extends RuntimeException {

    public GeneralException(String message) {
        super(message);
    }
}