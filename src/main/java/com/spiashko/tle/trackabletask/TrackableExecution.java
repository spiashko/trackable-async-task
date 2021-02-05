package com.spiashko.tle.trackabletask;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TrackableExecution {

    @NotNull
    private Long durationInMillis;
    @NotNull
    private LocalDateTime finishedAt;
    @NotNull
    private TrackableExecutionStatus status;
    private String statusMessage;

    @Builder(toBuilder = true)
    public TrackableExecution(Long durationInMillis,
                              LocalDateTime finishedAt,
                              TrackableExecutionStatus status,
                              String statusMessage) {
        this.durationInMillis = durationInMillis;
        this.finishedAt = finishedAt;
        this.status = status;
        this.statusMessage = statusMessage;
    }
}
