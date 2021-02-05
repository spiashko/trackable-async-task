package com.spiashko.rfetchexample.crudbase.repository;


import com.spiashko.rfetchexample.crudbase.entity.BaseJournalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface BaseJournalRepository<T extends BaseJournalEntity>
        extends JpaRepository<T, UUID>, JpaSpecificationExecutor<T> {
}
