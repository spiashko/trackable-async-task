package com.spiashko.trackabletask.trackabletask;


import com.spiashko.trackabletask.crudbase.BaseSearchService;

import java.util.List;
import java.util.UUID;

public interface TrackableTaskSearchService extends BaseSearchService<TrackableTask> {

    List<UUID> findIdsOfAllNotProcessed();

    TrackableTask findOneAndLock(UUID id);

}
