package com.example.petproject.services;

import com.example.petproject.entity.Person;
import com.example.petproject.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service

public class PeopleBaseService implements PeopleService{

    private final PeopleRepository repository;

    @Autowired
    public PeopleBaseService(PeopleRepository peopleRepository) {
        this.repository = peopleRepository;
    }

    public List<Person> getAll() {
        return repository.findAll();
    }

    public void create(Person person) {
        enrichPerson(person);

        repository.save(person);
    }

    @Override
    public Person delete(Integer id) {
        repository.deleteById(id);


        return null;
    }

    private void enrichPerson(Person person) {
        person.setCreatedAt(LocalDateTime.now());
        person.setUpdatedAt(LocalDateTime.now());
        person.setCreatedWho("admin");
    }

    public Person get(Integer id) {
        return repository.findById(id).orElse(null);
    }

}
