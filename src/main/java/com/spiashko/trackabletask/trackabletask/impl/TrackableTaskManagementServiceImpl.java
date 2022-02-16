package com.spiashko.trackabletask.trackabletask.impl;

import com.spiashko.trackabletask.trackabletask.TrackableTask;
import com.spiashko.trackabletask.trackabletask.TrackableTaskManagementService;
import com.spiashko.trackabletask.trackabletask.appevent.TrackableTaskCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
class TrackableTaskManagementServiceImpl implements TrackableTaskManagementService {

    private final TrackableTaskRepository repository;
    private final ApplicationEventPublisher publisher;

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public TrackableTask create(TrackableTask entityToCreate) {
        TrackableTask entity = repository.save(entityToCreate);
        TrackableTaskCreatedEvent event = TrackableTaskCreatedEvent.builder()
                .trackableTaskId(entity.getId())
                .build();
        publisher.publishEvent(event);
        return entity;
    }

}
