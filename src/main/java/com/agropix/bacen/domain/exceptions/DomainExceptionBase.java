package com.agropix.bacen.domain.exceptions;

public abstract class DomainExceptionBase extends RuntimeException {
    public DomainExceptionBase(String message) {
        super(message);
    }
}
