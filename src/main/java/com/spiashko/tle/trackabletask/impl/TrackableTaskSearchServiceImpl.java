package com.spiashko.tle.trackabletask.impl;

import com.spiashko.tle.crudbase.BaseSearchServiceImpl;
import com.spiashko.tle.trackabletask.TrackableTask;
import com.spiashko.tle.trackabletask.TrackableTaskSearchService;
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
