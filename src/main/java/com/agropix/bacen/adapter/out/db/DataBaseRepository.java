package com.agropix.bacen.adapter.out.db;

import com.agropix.bacen.application.port.out.DataBasePortOut;
import com.agropix.bacen.infrastructure.persistence.ChavePixPersistenceModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DataBaseRepository extends JpaRepository<ChavePixPersistenceModel, UUID>, DataBasePortOut {

}
