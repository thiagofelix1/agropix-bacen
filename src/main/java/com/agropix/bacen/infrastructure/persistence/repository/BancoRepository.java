package com.agropix.bacen.infrastructure.persistence.repository;

import com.agropix.bacen.infrastructure.persistence.model.BancoPersistenceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BancoRepository extends JpaRepository<BancoPersistenceModel, UUID> {
    boolean existsByCodigo(String codigo);
    BancoPersistenceModel getByCodigo(String codigo);
}
