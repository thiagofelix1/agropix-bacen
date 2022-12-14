package com.agropix.bacen.infrastructure.persistence.repository;

import com.agropix.bacen.infrastructure.persistence.model.ChavePixPersistenceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChavePixRepository extends JpaRepository<ChavePixPersistenceModel, UUID> {
    Optional<ChavePixPersistenceModel> findChavePixPersistenceModelByChave(String chave);
}
