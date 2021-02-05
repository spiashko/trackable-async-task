package com.spiashko.tle.trackabletask.processors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@RequiredArgsConstructor
@Component
public class TrackableTaskProcessorRegistry {

    private final List<TrackableTaskProcessor> processors;

    public TrackableTaskProcessor getProcessorByNameOrThrow(String name) {
        return processors.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("failed to find TrackableAsyncTaskProcessor by name=" + name));
    }


}
