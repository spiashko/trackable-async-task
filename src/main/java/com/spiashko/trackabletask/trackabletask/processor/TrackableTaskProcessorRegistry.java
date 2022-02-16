package com.spiashko.trackabletask.trackabletask.processor;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@SuppressWarnings("rawtypes")
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
