package com.spiashko.trackabletask.person;

import org.springframework.transaction.annotation.Transactional;

public interface PersonCreationService {

    @Transactional
    Person create(Person entityToCreate);

}
