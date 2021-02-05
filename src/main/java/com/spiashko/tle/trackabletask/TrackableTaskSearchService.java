package com.spiashko.tle.trackabletask;


import com.spiashko.tle.crudbase.BaseSearchService;

import java.util.List;
import java.util.UUID;

public interface TrackableTaskSearchService extends BaseSearchService<TrackableTask> {

    List<UUID> findIdsOfAllNotProcessed();

    TrackableTask findOneAndLock(UUID id);

}
