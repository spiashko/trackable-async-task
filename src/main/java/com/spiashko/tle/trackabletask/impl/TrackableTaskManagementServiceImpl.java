package com.spiashko.tle.trackabletask.impl;

import com.spiashko.tle.trackabletask.TrackableExecution;
import com.spiashko.tle.trackabletask.TrackableTask;
import com.spiashko.tle.trackabletask.TrackableTaskManagementService;
import com.spiashko.tle.trackabletask.TrackableTaskSearchService;
import com.spiashko.tle.trackabletask.appevent.TrackableTaskCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
class TrackableTaskManagementServiceImpl implements TrackableTaskManagementService {

    private final TrackableTaskSearchService searchService;
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

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public TrackableTask addExecution(UUID taskId, TrackableExecution execution) {
        TrackableTask entity = searchService.findOneOrThrow(taskId);
        entity.addExecution(execution);
        repository.save(entity);
        return entity;
    }

}
