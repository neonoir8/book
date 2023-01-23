package com.example.petproject.services;

import com.example.petproject.entity.Book;

import java.util.List;

public interface BookService {

     Book get(Integer id);

     List<Book> getAll();

     void create(Book book);

     Book delete(Integer id);

}
