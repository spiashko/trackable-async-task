package com.spiashko.rfetchexample.cat.impl;

import com.spiashko.rfetchexample.cat.Cat;
import com.spiashko.rfetchexample.cat.CatSearchService;
import com.spiashko.rfetchexample.crudbase.BaseSearchServiceImpl;
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
