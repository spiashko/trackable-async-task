package com.spiashko.trackabletask.trackabletask.impl;

import com.spiashko.trackabletask.crudbase.BaseSearchServiceImpl;
import com.spiashko.trackabletask.trackabletask.TrackableTask;
import com.spiashko.trackabletask.trackabletask.TrackableTaskSearchService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
class TrackableTaskSearchServiceImpl
        extends BaseSearchServiceImpl<TrackableTask, TrackableTaskRepository>
        implements TrackableTaskSearchService {

    public TrackableTaskSearchServiceImpl(
            TrackableTaskRepository repository) {
        super(repository);
    }

    @Override
    public List<UUID> findIdsOfAllNotProcessed() {
        return getRepository().findIdsOfAllNotProcessed();
    }

    @Override
    public TrackableTask findOneAndLock(UUID id) {
        return getRepository().findOneByIdAndLock(id);
    }
}
