package com.spiashko.tle.jsonblist;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import java.util.List;

@JsonSerialize(using = ItemsContainerSerializer.class)
@Getter
@Setter
@NoArgsConstructor
public class ItemsContainer<T> {

    @Valid
    @JsonUnwrapped
    private List<T> items;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public ItemsContainer(List<T> items) {
        this.items = items;
    }
}
