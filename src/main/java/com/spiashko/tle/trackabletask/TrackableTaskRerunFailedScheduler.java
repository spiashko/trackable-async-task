package com.spiashko.tle.trackabletask;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
class TrackableTaskRerunFailedScheduler {

    private final TrackableTaskSearchService searchService;
    private final TrackableTaskProcessorManager manager;

    @Scheduled(fixedDelay = 5000L)
    public void runNotProcessed() {

        List<UUID> allNotProcessed = searchService.findIdsOfAllNotProcessed();
        allNotProcessed.forEach(manager::process);

    }

}
