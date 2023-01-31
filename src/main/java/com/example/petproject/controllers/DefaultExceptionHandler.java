//package com.example.petproject.controllers;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.rest.webmvc.support.ExceptionMessage;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//@ControllerAdvice
//
//public class DefaultExceptionHandler {
//
//
//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    ExceptionMessage handle(IllegalArgumentException e){
//        return getExceptionMessage(e);
//    }
//
//    private ExceptionMessage getExceptionMessage(IllegalArgumentException e) {
//        return new ExceptionMessage(e);
//    }
//}
