package com.spiashko.tle.person.impl;

import com.spiashko.tle.crudbase.BaseSearchServiceImpl;
import com.spiashko.tle.person.Person;
import com.spiashko.tle.person.PersonSearchService;
import org.springframework.stereotype.Service;

@Service
class PersonSearchServiceImpl
        extends BaseSearchServiceImpl<Person, PersonRepository>
        implements PersonSearchService {

    public PersonSearchServiceImpl(PersonRepository repository) {
        super(repository);
    }
}
