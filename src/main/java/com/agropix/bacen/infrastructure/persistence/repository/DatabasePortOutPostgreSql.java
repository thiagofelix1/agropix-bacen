package com.agropix.bacen.infrastructure.persistence.repository;

import com.agropix.bacen.application.port.out.DataBasePortOut;
import com.agropix.bacen.domain.entities.Banco;
import com.agropix.bacen.domain.entities.ChavePix;
import com.agropix.bacen.domain.entities.Conta;
import com.agropix.bacen.domain.entities.PessoaFisica;
import com.agropix.bacen.domain.enums.TipoChavePix;
import com.agropix.bacen.infrastructure.persistence.model.ChavePixPersistenceModel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@Primary
@RequiredArgsConstructor
public class DatabasePortOutPostgreSql implements DataBasePortOut {

    private final ChavePixRepository chavePixRepository;
    private final ContaRepository contaRepository;
    private final PessoaFisicaRepository pessoaFisicaRepository;

    @Override
    public ChavePix save(ChavePix chave) {
        ChavePixPersistenceModel chavePixPersistenceModel = ChavePixPersistenceModel.fromEntity(chave);

        pessoaFisicaRepository.save(chavePixPersistenceModel.getConta().getTitular());
        contaRepository.save(chavePixPersistenceModel.getConta());
        chavePixRepository.save(chavePixPersistenceModel);

        return chave;
    }

    @Override
    public Optional<ChavePix> find(String chave) {
        Optional<ChavePixPersistenceModel> possivelChave = chavePixRepository.findChavePixPersistenceModelByChave(chave);

        if (possivelChave.isEmpty()) {
            return Optional.empty();
        } else {
            return possivelChave.map(DatabasePortOutPostgreSql::criaPessoaComEntidadePersistencia);
        }
    }

    private static ChavePix criaPessoaComEntidadePersistencia(ChavePixPersistenceModel chavePersistida) {
        return new ChavePix(chavePersistida.getChave(),
                new Banco(chavePersistida.getBanco().getNome(), chavePersistida.getBanco().getCodigo()),
                TipoChavePix.valueOf(chavePersistida.getTipoChave().getTipo()),
                new Conta(chavePersistida.getConta().getNumeroConta(), chavePersistida.getConta().getDigito(),
                        new PessoaFisica(chavePersistida.getConta().getTitular().getNome(),
                            chavePersistida.getConta().getTitular().getCpf(),
                            chavePersistida.getConta().getTitular().getEmail(),
                            chavePersistida.getConta().getTitular().getTelefone())));
    }
}
