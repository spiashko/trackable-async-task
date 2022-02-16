package com.spiashko.trackabletask.trackabletask.impl;

import com.spiashko.trackabletask.crudbase.repository.BaseRepository;
import com.spiashko.trackabletask.trackabletask.TrackableTask;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.UUID;

interface TrackableTaskRepository extends BaseRepository<TrackableTask> {

    @Query("select distinct t.id from TrackableTask t " +
            "where t.status <> 'PROCESSED' and t.retryCount < 15")
        // in real world here should be also restriction like "and t.plannedOn < (t.plannedOn - interval '1 hour')"
    List<UUID> findIdsOfAllNotProcessed();

    @QueryHints({
            @QueryHint(name = "javax.persistence.lock.timeout", value = "-2") // UPGRADE_SKIPLOCKED
    })
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select t from TrackableTask t " +
            "where t.id = :id")
    TrackableTask findOneByIdAndLock(UUID id);

}
