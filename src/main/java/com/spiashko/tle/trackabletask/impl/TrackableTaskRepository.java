package com.spiashko.tle.trackabletask.impl;

import com.spiashko.tle.crudbase.repository.BaseJournalRepository;
import com.spiashko.tle.trackabletask.TrackableTask;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.UUID;

interface TrackableTaskRepository extends BaseJournalRepository<TrackableTask> {

    @Query("select distinct t.id from TrackableTask t " +
            "where t.status <> 'PROCESSED'")
    List<UUID> findIdsOfAllNotProcessed();

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select t from TrackableTask t " +
            "where t.id = :id")
    TrackableTask findOneByIdAndLock(UUID id);

}
