package com.spiashko.tle.web.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.spiashko.tle.cat.Cat;
import com.spiashko.tle.cat.CatCreationService;
import com.spiashko.tle.cat.CatSearchService;
import com.spiashko.tle.crudbase.View;
import com.spiashko.tle.rfetch.RfetchSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CatRestController {

    private final CatCreationService creationService;
    private final CatSearchService searchService;

    @JsonView(View.Retrieve.class)
    @PostMapping("/cats")
    public Cat create(@JsonView(View.Create.class) @RequestBody Cat entityToCreate) {
        Cat result = creationService.create(entityToCreate);
        return result;
    }

    @JsonView(View.Retrieve.class)
    @GetMapping("/cats")
    public List<Cat> findAll(
            @RfetchSpec Specification<Cat> spec
    ) {
        List<Cat> result = searchService.findAll(spec);
        return result;
    }

}
