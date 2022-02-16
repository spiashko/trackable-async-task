package com.spiashko.trackabletask.trackabletask;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface TrackableTaskManagementService {

    @Transactional(propagation = Propagation.MANDATORY)
    TrackableTask create(TrackableTask entityToCreate);

}
