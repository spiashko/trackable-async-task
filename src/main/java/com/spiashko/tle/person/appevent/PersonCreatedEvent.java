package com.spiashko.tle.person.appevent;

import com.spiashko.tle.person.Person;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PersonCreatedEvent {

    private final Person person;

}
