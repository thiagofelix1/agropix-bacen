package com.agropix.bacen.infrastructure.persistence.model;

import com.agropix.bacen.domain.entities.Banco;
import com.agropix.bacen.domain.entities.ChavePix;
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
@Table(name = "chave_pix")
public class ChavePixPersistenceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_chave", nullable = false)
    private TipoChavePixPersistenceModel tipoChave;

    @Column(name = "chave", unique = true, nullable = false)
    private String chave;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "banco", nullable = false)
    private BancoPersistenceModel banco;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "conta", nullable = false)
    private ContaPersistenceModel conta;

    // ToDo: Criar Teste ChavePixPersistenceModel
    public static ChavePixPersistenceModel fromEntity(ChavePix entity) {
        ChavePixPersistenceModel chave = new ChavePixPersistenceModel();

        TipoChavePixPersistenceModel tipoChave = new TipoChavePixPersistenceModel();
        tipoChave.setTipo(entity.getTipo().toString());

        BancoPersistenceModel banco = new BancoPersistenceModel();
        banco.setCodigo(entity.getBanco().getCodigo());
        banco.setNome(entity.getBanco().getNome());

        PessoaFisicaPersistenceModel pessoaFisica = new PessoaFisicaPersistenceModel();
        pessoaFisica.setNome(entity.getConta().getTitular().getNome());
        pessoaFisica.setEmail(entity.getConta().getTitular().getEmail());
        pessoaFisica.setCpf(entity.getConta().getTitular().getCpf());
        pessoaFisica.setTelefone(entity.getConta().getTitular().getTelefone());

        ContaPersistenceModel conta = new ContaPersistenceModel();
        conta.setDigito(entity.getConta().getDigito());
        conta.setNumeroConta(entity.getConta().getNumeroConta());
        conta.setTitular(pessoaFisica);

        chave.tipoChave = tipoChave;
        chave.chave = entity.getChave();
        chave.banco = banco;
        chave.conta = conta;

        return chave;
    }
}
