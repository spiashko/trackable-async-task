package com.spiashko.tle.cat.impl;

import com.spiashko.tle.cat.Cat;
import com.spiashko.tle.cat.CatCreationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class CatCreationServiceImpl implements CatCreationService {

    private final CatRepository repository;

    @Override
    public Cat create(Cat entityToCreate) {
        Cat entity = repository.save(entityToCreate);
        return entity;
    }

}
