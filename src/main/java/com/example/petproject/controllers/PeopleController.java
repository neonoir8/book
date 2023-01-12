package com.example.petproject.controllers;

import com.example.petproject.models.Person;
import com.example.petproject.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;

    }

    @GetMapping
    public List<Person> getPeople() {
        return peopleService.findAll();
    }
}
