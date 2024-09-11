package com.example.S2D2.controllers;


import com.example.S2D2.entities.Autore;
import com.example.S2D2.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    private Autore createAuthor(@RequestBody Autore autore){
        return authorService.saveAuthor(autore);
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
