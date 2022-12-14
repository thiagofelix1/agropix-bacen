package com.agropix.bacen.infrastructure.persistence.model;

import com.agropix.bacen.domain.entities.Banco;
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
@Table(name = "banco")
public class BancoPersistenceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @Column(name = "codigo", nullable = false, unique = true)
    private String codigo;

    public static BancoPersistenceModel fromEntity(Banco entity) {
        var banco = new BancoPersistenceModel();

        banco.codigo = entity.getCodigo();
        banco.nome = entity.getNome();

        return banco;
    }
}
