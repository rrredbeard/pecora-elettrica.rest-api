package com.pecora_elettrica.api.controller;

import com.pecora_elettrica.api.facade.BookDTOFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookDTOFacade bookService;

    @GetMapping("/all")
    public @ResponseBody ResponseEntity<?> getAllBooks() {
        return bookService.getAll().toResponseEntity();
    }
}