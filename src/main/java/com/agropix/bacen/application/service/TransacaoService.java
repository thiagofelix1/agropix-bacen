package com.agropix.bacen.application.service;

import com.agropix.bacen.adapter.in.web.dto.request.transacao.TransacaoRequest;
import com.agropix.bacen.adapter.in.web.dto.response.TransacaoResponse;
import com.agropix.bacen.adapter.out.TransacaoPixPortOut;
import com.agropix.bacen.application.exceptions.PedidoTransacaoInvalidoException;
import com.agropix.bacen.application.port.out.DataBasePortOut;
import com.agropix.bacen.domain.entities.ChavePix;
import com.agropix.bacen.domain.entities.TransacaoPix;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class TransacaoService {

    private final DataBasePortOut repository;
    private final TransacaoPixPortOut transacaoHttpClient;

    public TransacaoService(DataBasePortOut repository, TransacaoPixPortOut transacaoHttpClient) {
        this.repository = repository;
        this.transacaoHttpClient = transacaoHttpClient;
    }

    public TransacaoResponse executarTransacao(TransacaoRequest request) {

        validaRequest(request);
        TransacaoPix transacao = request.toEntity();

        return null;
    }

    private void validaRequest(TransacaoRequest request) {

        ArrayList<String> erros = new ArrayList<>();

        Optional<ChavePix> possivelChaveOrigem = repository.find(request.getChaveOrigem());

        if (possivelChaveOrigem.isEmpty()) {
            erros.add(String.format("Sem chave salva para o valor da chave de origem %s", request.getChaveOrigem()));
        }

        Optional<ChavePix> possivelChaveDestino = repository.find(request.getChaveDestino());

        if (possivelChaveDestino.isEmpty()) {
            erros.add(String.format("Sem chave salva para o valor da chave de destino %s", request.getChaveOrigem()));
        }

        if (request.getValor() <= 0) {
            erros.add(String.format("O valor informado na transacao (%s) nao e um valor valido", request.getValor()));
        }

        if (!erros.isEmpty()) {
            throw new PedidoTransacaoInvalidoException(erros);
        }
    }
}
