package com.agropix.bacen.infrastructure.persistence.repository;

import com.agropix.bacen.infrastructure.persistence.model.UrlNotificacaoPersistenceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UrlNotificacaoRepository extends JpaRepository<UrlNotificacaoPersistenceModel, UUID> {
    Optional<UrlNotificacaoPersistenceModel> findUrlNotificacaoPersistenceModelByBanco(String nomeBanco);
}
