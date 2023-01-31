package com.example.petproject.services;

import com.example.petproject.entity.Book;
import com.example.petproject.entity.Person;
import com.example.petproject.repository.PeopleRepository;
import com.example.petproject.services.exception.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service

public class PeopleBaseService implements PeopleService{

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleBaseService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> getAll() {
        return peopleRepository.findAll();
    }

    public void createPerson(Person person) {
        enrichPerson(person);

        peopleRepository.save(person);
    }

    @Override
    public void deletePerson(Integer id) {
        peopleRepository.findById(id).orElseThrow(() ->
                new PersonNotFoundException("Person", "Id", id));
        peopleRepository.deleteById(id);

    }

    @Override
    public Person updatePerson(Person person, Integer id) {
        Person existingPerson = peopleRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        existingPerson.setName(person.getName());
        existingPerson.setAge(person.getAge());
        peopleRepository.save(existingPerson);

        return existingPerson;
    }

    private void enrichPerson(Person person) {
        person.setCreatedAt(LocalDateTime.now());
        person.setUpdatedAt(LocalDateTime.now());
        person.setCreatedWho("admin");
    }

    public Person get(Integer id) {
        return peopleRepository.findById(id).orElse(null);
    }

    public List<Book> getBookByPersonId(int id) {
        Optional<Person> person = peopleRepository.findById(id);

        return person.get().getBooks();
    }



}
