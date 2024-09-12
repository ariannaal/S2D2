package com.example.S2D2.controllers;


import com.example.S2D2.entities.Autore;
import com.example.S2D2.exceptions.BadRequestException;
import com.example.S2D2.payloads.NewAuthorDTO;
import com.example.S2D2.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    // http://localhost:3001/authors
    @GetMapping
    public List<Autore> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    // http://localhost:3001/authors / {id}
//    @GetMapping("/{id}")
//    private Autore getSingleAuthor(@PathVariable int id){
//        return authorService.findAuthorById(id);
//    }

    // 2. POST http://localhost:3001/authors (+ post)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Autore createAuthor(@RequestBody @Validated NewAuthorDTO payload, BindingResult validationResult){
        if (validationResult.hasErrors()) {
            String messages = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload. " + messages);
        } else {
            return authorService.save(payload);
        }
    }


    @PutMapping("/{id}")
    private Autore updateAuthor(@PathVariable int id, @RequestBody Autore autore){
        return authorService.findAuthorAndUpdate(id, autore);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteAuthor(@PathVariable int id){
        authorService.deleteAuthor(id);
    }


}
