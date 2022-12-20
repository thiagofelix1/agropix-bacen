package com.agropix.bacen.application.exceptions;

public class ItemNaoEncontradoException extends RuntimeException {
    public ItemNaoEncontradoException(String message) {
        super(message);
    }
}
