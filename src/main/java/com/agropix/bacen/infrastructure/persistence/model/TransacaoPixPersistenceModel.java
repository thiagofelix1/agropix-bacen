package com.agropix.bacen.infrastructure.persistence.model;

import com.agropix.bacen.domain.entities.TransacaoPix;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity(name = "transacao_pix")
public class TransacaoPixPersistenceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "epoch_second_transacao")
    private Long epochSecondTransacao;

    @Column(name = "chave_origem")
    private String chaveOrigem;

    @Column(name = "chave_destino")
    private String chaveDestino;
    private BigDecimal valor;

    public static TransacaoPixPersistenceModel fromEntity(TransacaoPix entity) {
        TransacaoPixPersistenceModel model = new TransacaoPixPersistenceModel();
        
        model.epochSecondTransacao = ZonedDateTime.now().toEpochSecond();
        model.chaveDestino = entity.getChaveDestino().getChave();
        model.chaveOrigem = entity.getChaveOrigem().getChave();
        model.valor = entity.getValor();

        return model;
    }
}
