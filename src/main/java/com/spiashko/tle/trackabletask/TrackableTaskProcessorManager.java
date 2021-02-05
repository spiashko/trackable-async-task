package com.spiashko.tle.trackabletask;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spiashko.tle.trackabletask.processors.TrackableTaskProcessor;
import com.spiashko.tle.trackabletask.processors.TrackableTaskProcessorRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Component
class TrackableTaskProcessorManager {

    private final TrackableTaskSearchService searchService;
    private final TrackableTaskManagementService managementService;
    private final TrackableTaskProcessorRegistry registry;
    private final ObjectMapper objectMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TrackableTask process(UUID taskId) {
        TrackableTask task = searchService.findOneAndLock(taskId);

        if (task.getExecutions() != null) {
            boolean processed = task.getExecutions().stream()
                    .anyMatch(te -> TrackableExecutionStatus.SUCCESS.equals(te.getStatus()));
            if (processed) {
                return task;
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

        return managementService.addExecution(task.getId(), execution);
    }

}
