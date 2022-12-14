package com.agropix.bacen.infrastructure.persistence.model;

import com.agropix.bacen.domain.entities.Conta;
import com.agropix.bacen.domain.entities.PessoaFisica;
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
@Table(name = "pessoa_fisica")
public class PessoaFisicaPersistenceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "telefone", nullable = false, unique = true)
    private String telefone;

    public static PessoaFisicaPersistenceModel fromEntity(PessoaFisica entity) {
        PessoaFisicaPersistenceModel pessoaFisica = new PessoaFisicaPersistenceModel();

        pessoaFisica.cpf = entity.getCpf();
        pessoaFisica.email = entity.getEmail();
        pessoaFisica.nome = entity.getNome();
        pessoaFisica.telefone = entity.getTelefone();


        return pessoaFisica;
    }
}
