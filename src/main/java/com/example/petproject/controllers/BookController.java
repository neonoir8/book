package com.example.petproject.controllers;

import com.example.petproject.dto.BookDTO;
import com.example.petproject.dto.PersonDTO;
import com.example.petproject.models.Book;
import com.example.petproject.models.Person;
import com.example.petproject.repository.BookRepository;
import com.example.petproject.services.BookService;
import com.example.petproject.util.BookErrorResponse;
import com.example.petproject.util.BookNotCreatedException;
import com.example.petproject.util.BookNotFoundException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;
    private final ModelMapper modelMapper;

    @Autowired
    public BookController(BookService bookService, ModelMapper modelMapper) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<BookDTO> getBook() {
        return bookService.findAll().stream().map(this::convertToBookDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BookDTO getBook(@PathVariable("id") int id) {
        return convertToBookDTO(bookService.findOne(id));

    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid BookDTO bookDTO,
                                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();


           List<FieldError> errors =  bindingResult.getFieldErrors();
           for(FieldError error : errors){
               errorMsg.append(error.getField())
                       .append(" - ").append(error.getDefaultMessage())
                       .append(";");
           }

           throw new BookNotCreatedException(errorMsg.toString());
        }

        bookService.save(convertToBook(bookDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }


    @ExceptionHandler
    private ResponseEntity<BookErrorResponse> handleException(BookNotFoundException e) {
        BookErrorResponse response = new BookErrorResponse(
                "Book with this id is wasn't found",
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<BookErrorResponse> handleException(BookNotCreatedException e) {
        BookErrorResponse response = new BookErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Book convertToBook(BookDTO bookDTO) {
        return modelMapper.map(bookDTO, Book.class);

    }
    private BookDTO convertToBookDTO(Book book) {
        return modelMapper.map(book, BookDTO.class);

    }
}
