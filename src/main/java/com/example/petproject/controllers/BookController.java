package com.example.petproject.controllers;

import com.example.petproject.entity.Book;
import com.example.petproject.services.BookService;
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
    private final BookService service;
    private final BookConverter converter;

    @Autowired
    public BookController(BookService service, BookConverter converter) {
        this.service = service;
        this.converter = converter;
    }


    @GetMapping
    public List<BookDTO> getBook() {
        return service.getAll().stream().map(converter::convertToBookDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BookDTO create(@PathVariable Integer id) {
        Book book = service.get(id);
        return converter.convertToBookDTO(book);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid BookDTO bookDTO) {
        service.create(converter.convertToBook(bookDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
