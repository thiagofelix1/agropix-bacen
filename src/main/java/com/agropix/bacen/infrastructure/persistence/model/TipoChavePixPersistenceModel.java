package com.agropix.bacen.infrastructure.persistence.model;

import com.agropix.bacen.domain.entities.PessoaFisica;
import com.agropix.bacen.domain.enums.TipoChavePix;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tipo_chave")
public class TipoChavePixPersistenceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "tipo", nullable = false, unique = true)
    private String tipo;

    public static TipoChavePixPersistenceModel fromEntity(TipoChavePix entity) {
        TipoChavePixPersistenceModel tipoChave = new TipoChavePixPersistenceModel();

        tipoChave.tipo = entity.name();

        return tipoChave;
    }
}
