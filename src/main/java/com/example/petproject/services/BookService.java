package com.example.petproject.services;

import com.example.petproject.entity.Book;

import java.util.List;

public interface BookService {

     Book get(Integer id);

     public List<Book> getAll();

     public void create(Book book);

}
