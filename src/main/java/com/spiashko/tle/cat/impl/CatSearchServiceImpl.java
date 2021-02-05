package com.spiashko.tle.cat.impl;

import com.spiashko.tle.cat.Cat;
import com.spiashko.tle.cat.CatSearchService;
import com.spiashko.tle.crudbase.BaseSearchServiceImpl;
import org.springframework.stereotype.Service;

@Service
class CatSearchServiceImpl
        extends BaseSearchServiceImpl<Cat, CatRepository>
        implements CatSearchService {

    public CatSearchServiceImpl(
            CatRepository repository) {
        super(repository);
    }
}
