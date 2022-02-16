package com.spiashko.trackabletask.web.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.spiashko.trackabletask.crudbase.View;
import com.spiashko.trackabletask.person.Person;
import com.spiashko.trackabletask.person.PersonCreationService;
import com.spiashko.trackabletask.person.PersonSearchService;
import lombok.RequiredArgsConstructor;
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
    public List<Person> findAll() {
        List<Person> result = searchService.findAll();
        return result;
    }

}
