package com.example.petproject.controllers;

import com.example.petproject.entity.Person;
import com.example.petproject.services.converter.PeopleConverter;
import com.example.petproject.services.dto.PersonDTO;
import com.example.petproject.services.PeopleBaseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/people")
public class PeopleController {

    private final PeopleBaseService service;
    private final PeopleConverter converter;


    @Autowired
    public PeopleController(PeopleBaseService service, PeopleConverter converter) {
        this.service = service;
        this.converter = converter;

    }

    @GetMapping
    public List<PersonDTO> getPeople() {
        return service.getAll().stream().map(converter::convertToPersonDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PersonDTO getPerson(@PathVariable("id") int id) {
        Person person = service.get(id);
        return converter.convertToPersonDTO(person);

    }

    @PostMapping
    @ResponseStatus
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid PersonDTO personDTO) {
        service.create(converter.convertToPerson(personDTO));

        return ResponseEntity.ok(HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<HttpStatus> delete(@PathVariable Integer id) {
        Person person = service.delete(id);

        return ResponseEntity.ok(HttpStatus.OK);
    }


}

