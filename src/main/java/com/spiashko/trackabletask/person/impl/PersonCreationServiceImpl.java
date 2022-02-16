package com.spiashko.trackabletask.person.impl;

import com.spiashko.trackabletask.person.Person;
import com.spiashko.trackabletask.person.PersonCreationService;
import com.spiashko.trackabletask.person.appevent.PersonCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
class PersonCreationServiceImpl implements PersonCreationService {

    private final PersonRepository repository;
    private final ApplicationEventPublisher publisher;

    @Transactional
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
