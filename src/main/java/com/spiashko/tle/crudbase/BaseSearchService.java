package com.spiashko.tle.crudbase;

import com.spiashko.tle.crudbase.entity.BaseJournalEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BaseSearchService<E extends BaseJournalEntity> {

    List<E> findAll();

    List<E> findAll(Specification<E> spec);

    Optional<E> findOne(UUID id);

    Optional<E> findOne(Specification<E> spec);

    E findOneOrThrow(UUID id);

    E findOneOrThrow(Specification<E> spec);

}
