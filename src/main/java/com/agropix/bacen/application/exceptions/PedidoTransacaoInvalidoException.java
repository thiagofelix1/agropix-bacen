package com.agropix.bacen.application.exceptions;

import java.util.Collection;

public class PedidoTransacaoInvalidoException extends RuntimeException {

    private final Collection<String> erros;

    public PedidoTransacaoInvalidoException(Collection<String> erros) {
        this.erros = erros;
    }

    public Collection<String> getErros() {
        return this.erros;
    }

}
