package com.example.petproject.services;

import com.example.petproject.entity.Book;
import com.example.petproject.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookBaseService implements BookService {
    private final BookRepository repository;

    @Autowired
    public BookBaseService(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Book> getAll() {
        return repository.findAll();
    }

    @Override
    public void create(Book book) {
        enrichBook(book);
        repository.save(book);
    }

    @Override
    public Book delete(Integer id) {


        repository.deleteById(id);
        return null;
    }

    private void enrichBook(Book book) {
        book.setCreatedAt(LocalDateTime.now());
        book.setUpdatedAt(LocalDateTime.now());
        book.setAuthor("ADMIN");
    }

    @Override
    public Book get(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
