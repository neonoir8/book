package com.example.petproject.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="book")
public class Book {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name_of_book")
    private String nameOfBook;

    @Column(name="year_of_publishing")
    private int yearOfPublishing;

    @ManyToMany(mappedBy = "books")
    private List<Person> persons;

    public Book() {

    }

    public Book(String nameOfBook, int yearOfPublishing) {
        this.nameOfBook = nameOfBook;
        this.yearOfPublishing = yearOfPublishing;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameOfBook() {
        return nameOfBook;
    }

    public void setNameOfBook(String nameOfBook) {
        this.nameOfBook = nameOfBook;
    }

    public int getYearOfPublishing() {
        return yearOfPublishing;
    }

    public void setYearOfPublishing(int yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", nameOfBook='" + nameOfBook + '\'' +
                ", yearOfPublishing=" + yearOfPublishing +
                '}';
    }
}
