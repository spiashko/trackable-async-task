package com.spiashko.rfetchexample.web.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.spiashko.rfetchexample.crudbase.View;
import com.spiashko.rfetchexample.person.Person;
import com.spiashko.rfetchexample.person.PersonCreationService;
import com.spiashko.rfetchexample.person.PersonSearchService;
import com.spiashko.rfetchexample.rfetch.RfetchSpec;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/persons")
public class PersonRestController {

    private final PersonCreationService creationService;
    private final PersonSearchService searchService;

    @JsonView(View.Retrieve.class)
    @PostMapping
    public Person create(@JsonView(View.Create.class) @RequestBody Person entityToCreate) {
        Person result = creationService.create(entityToCreate);
        return result;
    }

    @JsonView(View.Retrieve.class)
    @GetMapping
    public List<Person> findAll(
            @Parameter(hidden = true)
            @RfetchSpec Specification<Person> spec
    ) {
        List<Person> result = searchService.findAll(spec);
        return result;
    }

}
