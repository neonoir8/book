package com.example.petproject.services;

import com.example.petproject.models.Book;
import com.example.petproject.repository.BookRepository;
import com.example.petproject.util.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findOne(int id) {
        Optional<Book> foundBook = bookRepository.findById(id);
        return foundBook.orElseThrow(BookNotFoundException::new);

    }

    @Transactional
    public void save(Book book) {
        enrichBook(book);

        bookRepository.save(book);
    }

    private void enrichBook(Book book) {
        book.setCreatedAt(LocalDateTime.now());
        book.setUpdatedAt(LocalDateTime.now());
        book.setAuthor("ADMIN");
    }
}
