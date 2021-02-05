package com.spiashko.tle.person;

import org.springframework.transaction.annotation.Transactional;

public interface PersonCreationService {

    @Transactional
    Person create(Person entityToCreate);

}
