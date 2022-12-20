package com.agropix.bacen.adapter.out;

import com.agropix.bacen.application.port.out.DataBasePortOut;
import com.agropix.bacen.domain.entities.*;
import com.agropix.bacen.domain.enums.TipoChavePix;
import com.agropix.bacen.infrastructure.persistence.model.*;
import com.agropix.bacen.infrastructure.persistence.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// ToDo: Criar Teste DatabasePortOutPostgreSql

@Repository
@Primary
@RequiredArgsConstructor
public class DatabasePortOutPostgreSql implements DataBasePortOut {

    private final ChavePixRepository chavePixRepository;
    private final ContaRepository contaRepository;
    private final PessoaFisicaRepository pessoaFisicaRepository;
    private final TipoChavePixRepository tipoChavePixRepository;
    private final BancoRepository bancoRepository;
    private final TransacaoRepository transacaoRepository;
    private final UrlNotificacaoRepository urlNotificacaoRepository;

    @Override
    public ChavePix save(ChavePix chave) {
        ChavePixPersistenceModel chavePixPersistenceModel = ChavePixPersistenceModel.fromEntity(chave);

        TipoChavePix tipoChavePix = chave.getTipo();
        TipoChavePixPersistenceModel tipoChavePixPersistenceModel = tipoChavePixRepository.findByTipo(tipoChavePix.name())
                .orElseThrow(() -> new RuntimeException("Tipo chave pix não encontrado!"));

        chavePixPersistenceModel.setTipoChave(tipoChavePixPersistenceModel);

        PessoaFisica pessoaFisica = chave.getConta().getTitular();
        PessoaFisicaPersistenceModel pessoaFisicaPersistenceModel;
        if(pessoaFisicaRepository.existsByCpf(pessoaFisica.getCpf())) {
            pessoaFisicaPersistenceModel = pessoaFisicaRepository.findByCpf(pessoaFisica.getCpf());
        } else {
            pessoaFisicaPersistenceModel = pessoaFisicaRepository.saveAndFlush(PessoaFisicaPersistenceModel.fromEntity(pessoaFisica));
        }

        Conta conta = chave.getConta();
        ContaPersistenceModel contaPersistenceModel;
        if(contaRepository.existsByNumeroConta(conta.getNumeroConta())) {
            contaPersistenceModel = contaRepository.findByNumeroConta(conta.getNumeroConta());
        } else {
            contaPersistenceModel = ContaPersistenceModel.fromEntity(conta);
            contaPersistenceModel.setTitular(pessoaFisicaPersistenceModel);
            contaPersistenceModel = contaRepository.saveAndFlush(contaPersistenceModel);
        }
        chavePixPersistenceModel.setConta(contaPersistenceModel);

        Banco banco = chave.getBanco();
        BancoPersistenceModel bancoPersistenceModel;
        if(bancoRepository.existsByCodigo(banco.getCodigo())) {
            bancoPersistenceModel = bancoRepository.getByCodigo(banco.getCodigo());
        } else {
            bancoPersistenceModel = bancoRepository.saveAndFlush(BancoPersistenceModel.fromEntity(banco));
        }
        chavePixPersistenceModel.setBanco(bancoPersistenceModel);

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

    @Override
    public void salvarTransacao(TransacaoPix transacao) {
        TransacaoPixPersistenceModel persistenceModel = TransacaoPixPersistenceModel.fromEntity(transacao);
        transacaoRepository.save(persistenceModel);
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

    public String getUrlNotificacao(String nomeBanco) {
        Optional<UrlNotificacaoPersistenceModel> possivelUrl = urlNotificacaoRepository.findUrlNotificacaoPersistenceModelByBanco(nomeBanco);
        if (possivelUrl.isPresent()) {
            return possivelUrl.get().getUrl();
        } else {
            // Não deveria aconter
            throw new RuntimeException("Não deveria aconter");
        }
    }
}
