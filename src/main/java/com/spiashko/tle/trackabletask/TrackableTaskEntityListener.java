package com.spiashko.tle.trackabletask;

import com.spiashko.tle.trackabletask.processors.TrackableTaskProcessor;
import com.spiashko.tle.trackabletask.processors.TrackableTaskProcessorRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.List;
import java.util.stream.Collectors;

@Configurable
class TrackableTaskEntityListener {

    @Autowired
    private TrackableTaskProcessorRegistry registry;

    @PrePersist
    public void validateOnPrePersist(TrackableTask entity) {

        String name = entity.getType();
        Class<?> inputClass = entity.getParams().getClass();

        TrackableTaskProcessor processor = registry.getProcessorByNameOrThrow(name);
        if (!processor.getInputClass().equals(inputClass)) {
            throw new IllegalStateException("pair: name=" + name + " class=" +
                    inputClass.getSimpleName() + " is not valid");
        }

        List<TrackableExecution> executions = entity.getExecutions();
        if (CollectionUtils.isEmpty(executions)) {
            Assert.state(entity.getRetryCount() == 0, "on empty executions retryCount must be 0");
            Assert.state(TrackableTaskStatus.NEW.equals(entity.getStatus()),
                    "on empty executions status must be NEW");
        } else {
            throw new IllegalStateException("on create executions must be empty");
        }
    }

    @PreUpdate
    public void validateOnPreUpdate(TrackableTask entity) {

        List<TrackableExecution> executions = entity.getExecutions();
        if (CollectionUtils.isEmpty(executions)) {
            throw new IllegalStateException("on update executions must be not empty");
        } else {
            Assert.state(entity.getRetryCount() == executions.size(),
                    "retryCount and executions size must be the same");
            List<TrackableExecutionStatus> statuses = executions.stream()
                    .map(TrackableExecution::getStatus)
                    .collect(Collectors.toList());

            if (statuses.contains(TrackableExecutionStatus.SUCCESS)) {
                Assert.state(TrackableTaskStatus.PROCESSED.equals(entity.getStatus()),
                        "on existing success execution task status must be PROCESSED");
            } else if (statuses.stream().allMatch(TrackableExecutionStatus.FAIL::equals)) {
                Assert.state(TrackableTaskStatus.FAILED.equals(entity.getStatus()),
                        "on all fail executions task status must be FAILED");
            } else {
                throw new RuntimeException("unreachable");
            }
        }
    }

}
