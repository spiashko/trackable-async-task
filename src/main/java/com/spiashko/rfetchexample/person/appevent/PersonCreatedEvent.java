package com.spiashko.rfetchexample.person.appevent;

import com.spiashko.rfetchexample.person.Person;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PersonCreatedEvent {

    private final Person person;

}
