package com.agropix.bacen.infrastructure.persistence.model;

import com.agropix.bacen.domain.entities.ChavePix;
import com.agropix.bacen.domain.entities.TransacaoPix;
import org.apache.kafka.common.protocol.types.Field;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

@Entity(name = "transacao_pix")
public class TransacaoPixPersistenceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String chaveOrigem;
    private String chaveDestino;
    private BigDecimal valor;

    public static TransacaoPixPersistenceModel fromEntity(TransacaoPix entity) {
        TransacaoPixPersistenceModel model = new TransacaoPixPersistenceModel();

        model.chaveDestino = entity.getChaveDestino().getChave();
        model.chaveOrigem = entity.getChaveDestino().getChave();
        model.valor = entity.getValor();

        return model;
    }
}
