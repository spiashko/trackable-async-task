package com.spiashko.rfetchexample.person.impl;

import com.spiashko.rfetchexample.person.Person;
import com.spiashko.rfetchexample.person.PersonCreationService;
import com.spiashko.rfetchexample.person.appevent.PersonCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class PersonCreationServiceImpl implements PersonCreationService {

    private final PersonRepository repository;
    private final ApplicationEventPublisher publisher;

    @Override
    public Person create(Person entityToCreate) {
        Person entity = repository.save(entityToCreate);

        publisher.publishEvent(
                PersonCreatedEvent.builder()
                        .person(entity)
                        .build()
        );

        return entity;
    }

}
