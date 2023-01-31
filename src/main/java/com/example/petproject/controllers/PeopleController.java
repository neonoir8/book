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

    private final PeopleBaseService peopleBaseService;
    private final PeopleConverter converter;


    @Autowired
    public PeopleController(PeopleBaseService service, PeopleConverter converter) {
        this.peopleBaseService = service;
        this.converter = converter;

    }

    @GetMapping
    public List<PersonDTO> getPeople() {
        return peopleBaseService.getAll().stream().map(converter::convertToPersonDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PersonDTO getPerson(@PathVariable("id") int id) {
        Person person = peopleBaseService.get(id);
        return converter.convertToPersonDTO(person);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HttpStatus> createPerson(@RequestBody @Valid PersonDTO personDTO) {
        peopleBaseService.createPerson(converter.convertToPerson(personDTO));
        return ResponseEntity.ok(HttpStatus.OK);


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        peopleBaseService.deletePerson(id);
        return new ResponseEntity<String>("Пользователь успешно удален", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HttpStatus> updatePerson(@RequestBody PersonDTO personDTO, @PathVariable("id") Integer id) {
        peopleBaseService.updatePerson(converter.convertToPerson(personDTO), id);
        return ResponseEntity.ok(HttpStatus.OK);

    }


}

