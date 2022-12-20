package com.agropix.bacen.application.port.out;

import com.agropix.bacen.domain.entities.ChavePix;
import com.agropix.bacen.domain.entities.TransacaoPix;
import com.agropix.bacen.domain.entities.UrlNoticacao;

import java.util.Optional;

public interface DataBasePortOut {

    ChavePix save(ChavePix chave);
    Optional<ChavePix> find(String chave);
    String getUrlNotificacao(String nomeBanco);
    void salvarTransacao(TransacaoPix transacao);

}
