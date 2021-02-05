package com.spiashko.rfetchexample.person.impl;

import com.spiashko.rfetchexample.crudbase.BaseSearchServiceImpl;
import com.spiashko.rfetchexample.person.Person;
import com.spiashko.rfetchexample.person.PersonSearchService;
import org.springframework.stereotype.Service;

@Service
class PersonSearchServiceImpl
        extends BaseSearchServiceImpl<Person, PersonRepository>
        implements PersonSearchService {

    public PersonSearchServiceImpl(PersonRepository repository) {
        super(repository);
    }
}
