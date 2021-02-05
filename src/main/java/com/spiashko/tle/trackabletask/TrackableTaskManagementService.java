package com.spiashko.tle.trackabletask;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface TrackableTaskManagementService {

    @Transactional(propagation = Propagation.MANDATORY)
    TrackableTask create(TrackableTask entityToCreate);

    @Transactional(propagation = Propagation.MANDATORY)
    TrackableTask addExecution(UUID taskId, TrackableExecution execution);

}
