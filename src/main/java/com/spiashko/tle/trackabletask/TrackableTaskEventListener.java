package com.spiashko.tle.trackabletask;

import com.spiashko.tle.trackabletask.appevent.TrackableTaskCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Component
class TrackableTaskEventListener {

    private final TrackableTaskProcessorManager manager;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void processCreatedTask(TrackableTaskCreatedEvent event) {
        UUID taskId = event.getTrackableTaskId();
        manager.process(taskId);
    }

}
