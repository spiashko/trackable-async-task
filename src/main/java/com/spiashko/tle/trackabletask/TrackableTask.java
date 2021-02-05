package com.spiashko.tle.trackabletask;

import com.spiashko.tle.crudbase.entity.BaseJournalEntity;
import com.spiashko.tle.jsonblist.ItemsContainer;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
@EntityListeners({TrackableTaskEntityListener.class})
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "trackable_task")
public class TrackableTask extends BaseJournalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Valid
    @NotNull
    @Type(type = "jsonb")
    @Column(name = "params", nullable = false)
    private Object params;

    @NotNull
    @Column(name = "type", nullable = false)
    private String type;

    @NotNull
    @Column(name = "retry_count", nullable = false)
    private Integer retryCount;

    @NotNull
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TrackableTaskStatus status;

    @NotNull
    @Column(name = "planned_on", nullable = false)
    private LocalDateTime plannedOn;

    @Valid
    @Type(type = "jsonb")
    @Column(name = "executions")
    private ItemsContainer<TrackableExecution> executions;

    @Builder
    public TrackableTask(@NotNull Object params, @NotNull String type) {
        this.params = params;
        this.type = type;
        this.plannedOn = LocalDateTime.now();
        this.status = TrackableTaskStatus.NEW;
        this.retryCount = 0;
    }

    public TrackableTask addExecution(TrackableExecution execution) {
        Assert.notNull(execution, "execution must not be null");
        TrackableExecutionStatus executionStatus = execution.getStatus();
        Assert.notNull(executionStatus, "executionStatus must not be null");

        if (executions == null) {
            executions = new ItemsContainer<>(new ArrayList<>());
        }
        executions.getItems().add(execution);

        retryCount += 1;

        switch (executionStatus) {
            case FAIL:
                this.status = TrackableTaskStatus.FAILED;
                break;
            case SUCCESS:
                this.status = TrackableTaskStatus.PROCESSED;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + executionStatus);
        }

        return this;
    }

    public List<TrackableExecution> getExecutions() {
        if (executions == null) {
            return null;
        }
        return executions.getItems();
    }
}
