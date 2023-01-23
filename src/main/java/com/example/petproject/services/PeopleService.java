package com.example.petproject.services;



import com.example.petproject.entity.Person;

import java.util.List;

public interface PeopleService {

    Person get(Integer id);

    List<Person> getAll();

    void create(Person person);

    Person delete(Integer id);

}
