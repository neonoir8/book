package com.example.petproject.services;

import com.example.petproject.entity.Book;
import com.example.petproject.repository.BookRepository;
import com.example.petproject.services.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookBaseService implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookBaseService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void createBook(Book book) {
        enrichBook(book);
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(Integer id) {
        bookRepository.findById(id).orElseThrow(() ->
                new BookNotFoundException("Book", "Id", id));
        bookRepository.deleteById(id);
    }

    @Override
    public Book updateBook(Book book, Integer id) {
        Book existingBook = bookRepository.findById(id).orElse(null);

        existingBook.setNameOfBook(book.getNameOfBook());
        existingBook.setYearOfPublishing(book.getYearOfPublishing());
        bookRepository.save(existingBook);

        return existingBook;
    }


    private void enrichBook(Book book) {
        book.setCreatedAt(LocalDateTime.now());
        book.setUpdatedAt(LocalDateTime.now());
        book.setAuthor("ADMIN");
    }

    @Override
    public Book getBookById(Integer id) {
        return bookRepository.findById(id).orElse(null);
    }
}
