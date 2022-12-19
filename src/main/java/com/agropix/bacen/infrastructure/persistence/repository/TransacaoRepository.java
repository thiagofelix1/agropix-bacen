package com.agropix.bacen.infrastructure.persistence.repository;

import com.agropix.bacen.domain.entities.TransacaoPix;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransacaoRepository extends JpaRepository<TransacaoPix, UUID> {  }
