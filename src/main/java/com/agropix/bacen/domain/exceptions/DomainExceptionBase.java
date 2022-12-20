package com.agropix.bacen.domain.exceptions;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class DomainExceptionBase extends RuntimeException {
    private final List<String> errors;
    public DomainExceptionBase(String message) {
        super(message);
        this.errors = List.of(message);
    }

    public Collection<String> getErrors() {
        return Collections.unmodifiableList(errors);
    }

}
