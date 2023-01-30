package com.example.petproject.controllers;

import com.example.petproject.entity.Book;
import com.example.petproject.services.BookBaseService;
import com.example.petproject.services.converter.BookConverter;
import com.example.petproject.services.dto.BookDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")

public class BookController {
    private final BookBaseService bookBaseService;
    private final BookConverter converter;

    @Autowired
    public BookController(BookBaseService bookBaseService, BookConverter converter) {
        this.bookBaseService = bookBaseService;
        this.converter = converter;
    }

    @GetMapping
    public List<BookDTO> getBook() {
        return bookBaseService.getAllBooks().stream().map(converter::convertToBookDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BookDTO getBookById(@PathVariable Integer id) {
        Book book = bookBaseService.getBookById(id);
        return converter.convertToBookDTO(book);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HttpStatus> createBook(@RequestBody @Valid BookDTO bookDTO) {
        bookBaseService.createBook(converter.convertToBook(bookDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id) {
        bookBaseService.deleteBook(id);
        return new ResponseEntity<String>("Книга успешно удалена", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HttpStatus> updateBook( @RequestBody BookDTO bookDTO, @PathVariable("id") Integer id) {
        bookBaseService.updateBook(converter.convertToBook(bookDTO), id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}


