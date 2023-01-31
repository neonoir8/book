package com.example.petproject.services.converter;


import com.example.petproject.entity.Book;
import com.example.petproject.services.dto.BookDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class BookConverter {

    private final ModelMapper modelMapper;

    @Autowired
    public BookConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public BookDTO convertToBookDTO(Book book) {
        return modelMapper.map(book, BookDTO.class);
    }

    public Book convertToBook(BookDTO bookDTO) {
        return modelMapper.map(bookDTO, Book.class);
    }
}




