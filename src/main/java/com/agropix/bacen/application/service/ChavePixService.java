package com.agropix.bacen.application.service;

import com.agropix.bacen.adapter.in.web.dto.request.criacao_chave.CriacaoChavePixRequest;
import com.agropix.bacen.application.exceptions.ItemNaoEncontradoException;
import com.agropix.bacen.application.port.out.DataBasePortOut;
import com.agropix.bacen.domain.entities.ChavePix;
import com.agropix.bacen.domain.exceptions.ChavePixExistenteException;
import org.springframework.stereotype.Service;

@Service
public class ChavePixService {

    private final DataBasePortOut portaSaida;

    public ChavePixService(DataBasePortOut portaSaida) {
        this.portaSaida = portaSaida;
    }

    public ChavePix createChavePix(CriacaoChavePixRequest request) {
        if(portaSaida.find(request.getChave()).isPresent()) {
            throw new ChavePixExistenteException(String.format("A chave %s já existe.", request.getChave()));
        }
        ChavePix chave = CriacaoChavePixRequest.toEntity(request);

        return portaSaida.save(chave);
    }

    public ChavePix getChavePix(String chave) {
        return portaSaida.find(chave)
            .orElseThrow(() -> new ItemNaoEncontradoException(String.format("Sem chave encontrada para o valor %s", chave)));
    }

    // ToDo: Update ChavePixService

    // ToDo: Delete ChavePixService
}
