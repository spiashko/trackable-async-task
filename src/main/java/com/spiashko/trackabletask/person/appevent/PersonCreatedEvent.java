package com.spiashko.trackabletask.person.appevent;

import com.spiashko.trackabletask.person.Person;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PersonCreatedEvent {

    private final Person person;

}
