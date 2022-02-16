package com.spiashko.trackabletask.person.impl;

import com.spiashko.trackabletask.crudbase.BaseSearchServiceImpl;
import com.spiashko.trackabletask.person.Person;
import com.spiashko.trackabletask.person.PersonSearchService;
import org.springframework.stereotype.Service;

@Service
class PersonSearchServiceImpl
        extends BaseSearchServiceImpl<Person, PersonRepository>
        implements PersonSearchService {

    public PersonSearchServiceImpl(PersonRepository repository) {
        super(repository);
    }
}
