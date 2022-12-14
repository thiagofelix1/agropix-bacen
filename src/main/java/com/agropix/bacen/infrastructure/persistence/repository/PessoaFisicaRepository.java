package com.agropix.bacen.infrastructure.persistence.repository;

import com.agropix.bacen.infrastructure.persistence.model.PessoaFisicaPersistenceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisicaPersistenceModel, UUID> {
}
