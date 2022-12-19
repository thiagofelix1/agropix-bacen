package com.agropix.bacen.infrastructure.persistence.repository;

import com.agropix.bacen.infrastructure.persistence.model.UrlNoticacaoPersistenceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UrlNotificacaoRepository extends JpaRepository<UrlNoticacaoPersistenceModel, UUID> {
    Optional<UrlNoticacaoPersistenceModel> findUrlNoticacaoPersistenceModelByNomeBanco(String nomeBanco);
}
