package com.spiashko.trackabletask.trackabletask;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spiashko.trackabletask.trackabletask.appevent.TrackableTaskCreatedEvent;
import com.spiashko.trackabletask.trackabletask.processor.TrackableTaskProcessor;
import com.spiashko.trackabletask.trackabletask.processor.TrackableTaskProcessorRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;

@Slf4j
@RequiredArgsConstructor
@Component
class TrackableTaskExecutionManager {

    private final Executor applicationTaskExecutor;
    private final TransactionTemplate transactionTemplate;
    private final TrackableTaskSearchService searchService;
    private final TrackableTaskProcessorRegistry registry;
    private final ObjectMapper objectMapper;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void processCreatedTask(TrackableTaskCreatedEvent event) {
        log.info("submitting trackable task for execution right after creation");
        UUID taskId = event.getTrackableTaskId();
        processAsync(taskId);
    }

    @Scheduled(fixedDelay = 5000L)
    void runNotProcessed() {
        log.info("retry not processed started");
        List<UUID> allNotProcessed = searchService.findIdsOfAllNotProcessed();
        allNotProcessed.forEach(this::processAsync);
        log.info("retry not processed finished");
    }

    private void processAsync(UUID taskId) {
        applicationTaskExecutor.execute(() -> transactionTemplate.executeWithoutResult(s -> processInternal(taskId)));
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private void processInternal(UUID taskId) {
        TrackableTask task = searchService.findOneAndLock(taskId);

        if (task == null) {
            log.info("task with id={} not found - most probably task is already under execution", taskId);
            return;
        }

        if (task.getExecutions() != null) {
            boolean processed = task.getExecutions().stream()
                    .anyMatch(te -> TrackableExecutionStatus.SUCCESS.equals(te.getStatus()));
            if (processed) {
                log.info("task with id={} is already processed", taskId);
                return;
            }
        }

        RuntimeException processingException = null;
        Long startTime = System.currentTimeMillis();
        try {
            String type = task.getType();

            TrackableTaskProcessor processor = registry.getProcessorByNameOrThrow(type);

            Object params = task.getParams();
            Object convertValue = objectMapper.convertValue(params, processor.getInputClass());

            processor.process(convertValue);
        } catch (RuntimeException ex) {
            processingException = ex;
        }
        Long finishTime = System.currentTimeMillis();
        LocalDateTime finishedAt = LocalDateTime.now();

        TrackableExecution execution;
        if (processingException != null) {
            execution = TrackableExecution.builder()
                    .status(TrackableExecutionStatus.FAIL)
                    .statusMessage(processingException.getMessage())
                    .build();
        } else {
            execution = TrackableExecution.builder()
                    .status(TrackableExecutionStatus.SUCCESS)
                    .build();
        }

        execution = execution.toBuilder()
                .finishedAt(finishedAt)
                .durationInMillis(finishTime - startTime)
                .build();

        //logging for the case when after exiting from current method we fail to write to database
        log.info("task with id={} has execution results: {} ", taskId, execution);
        task.addExecution(execution);
    }

}
