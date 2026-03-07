package com.example.bookexchange.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookexchange.dto.AvailableBookDto;
import com.example.bookexchange.service.BookAvailabilityService;

@RestController
@RequestMapping("/books/available")
public class BookAvailabilityController {

    private final BookAvailabilityService service;

    public BookAvailabilityController(BookAvailabilityService service) {
        this.service = service;
    }

    @GetMapping
    public List<AvailableBookDto> getAvailableBooks(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String autor,
            @RequestParam(required = false, name = "genre") Long genreId,
            @RequestParam(required = false) Long excludeUserId) {
        return service.findAvailableBooks(titulo, autor, genreId, excludeUserId);
    }
}

