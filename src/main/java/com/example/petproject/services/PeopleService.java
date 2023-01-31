package com.example.petproject.services;



import com.example.petproject.entity.Person;

import java.util.List;

public interface PeopleService {

    Person get(Integer id);
    List<Person> getAll();
    void createPerson(Person person);
    void deletePerson(Integer id);
    Person updatePerson(Person person, Integer id);
}
