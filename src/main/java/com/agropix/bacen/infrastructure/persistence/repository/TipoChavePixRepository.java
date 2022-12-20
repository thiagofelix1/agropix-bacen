package com.agropix.bacen.infrastructure.persistence.repository;

import com.agropix.bacen.infrastructure.persistence.model.TipoChavePixPersistenceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TipoChavePixRepository extends JpaRepository<TipoChavePixPersistenceModel, UUID> {
    Optional<TipoChavePixPersistenceModel> findByTipo(String tipo);
}
