package com.spiashko.trackabletask.trackabletask.appevent;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Builder
@Getter
@RequiredArgsConstructor
public class TrackableTaskCreatedEvent {

    private final UUID trackableTaskId;

}
