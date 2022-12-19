package com.agropix.bacen.application.port.out;

import com.agropix.bacen.domain.entities.ChavePix;
import com.agropix.bacen.domain.entities.TransacaoPix;

import java.util.Optional;

public interface DataBasePortOut {

    ChavePix save(ChavePix chave);
    Optional<ChavePix> find(String chave);
    void salvarTransacao(TransacaoPix transacao);
}
