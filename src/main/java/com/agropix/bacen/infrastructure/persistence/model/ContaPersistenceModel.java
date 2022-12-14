package com.agropix.bacen.infrastructure.persistence.model;

import com.agropix.bacen.domain.entities.ChavePix;
import com.agropix.bacen.domain.entities.Conta;
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
@Table(name = "conta")
public class ContaPersistenceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "numero_conta", nullable = false, unique = true)
    private String numeroConta;

    @Column(name = "digito", nullable = false)
    private String digito;

    @OneToOne(optional = false)
    @JoinColumn(name = "titular", nullable = false, unique = true) // ! Por enquanto o cliente so pode ter 1 conta
    private PessoaFisicaPersistenceModel titular;

    public static ContaPersistenceModel fromEntity(Conta entity) {
        ContaPersistenceModel conta = new ContaPersistenceModel();


        PessoaFisicaPersistenceModel pessoaFisica = new PessoaFisicaPersistenceModel();
        pessoaFisica.setNome(entity.getTitular().getNome());
        pessoaFisica.setEmail(entity.getTitular().getEmail());
        pessoaFisica.setCpf(entity.getTitular().getCpf());
        pessoaFisica.setTelefone(entity.getTitular().getTelefone());

        conta.digito = entity.getDigito();
        conta.titular = pessoaFisica;
        conta.numeroConta = entity.getNumeroConta();

        return conta;
    }
}
