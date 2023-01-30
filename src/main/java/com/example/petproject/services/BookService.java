package com.example.petproject.services;

import com.example.petproject.entity.Book;
import com.example.petproject.entity.Person;

import java.util.List;

public interface BookService {

     Book getBookById(Integer id);

     List<Book> getAllBooks();

     void createBook(Book book);

     void deleteBook(Integer id);

     Book updateBook(Book book, Integer id);

}
