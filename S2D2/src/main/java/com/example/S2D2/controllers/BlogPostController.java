package com.example.S2D2.controllers;

import com.example.S2D2.entities.BlogPost;
import com.example.S2D2.entities.BlogPostPayload;
import com.example.S2D2.exceptions.BadRequestException;
import com.example.S2D2.exceptions.NotFoundException;
import com.example.S2D2.payloads.NewBlogPostDTO;
import com.example.S2D2.services.BlogPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/blogposts")
public class BlogPostController {

    @Autowired
    private BlogPostsService blogPostService;

    // http://localhost:3001/blogposts
    @GetMapping
    private Page<BlogPost> findAll(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "id") String sortBy){
        return this.blogPostService.findAll(page, size, sortBy);
    }

    // http://localhost:3001/blogposts / {id}
    @GetMapping("/{id}")
    private BlogPost getSinglePost(@PathVariable int id){
        return blogPostService.findById(id);
    }

    // 2. POST http://localhost:3001/blogposts (+ post)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost createPost(@RequestBody @Validated NewBlogPostDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String messages = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload. " + messages);
        } else {
            return blogPostService.savePost(payload);
        }
    }

        //endpoint per ricevere dati dell'immagine
        @PostMapping("/{id}/cover")
        public void uploadCoverPost(@PathVariable int id, @RequestParam("cover") MultipartFile image) throws IOException {
            blogPostService.uploadImage(id, image);
        }


        // PUT /blogposts/{id} => modifica lo specifico blog post
//    @PutMapping("/{id}")
//    public ResponseEntity<BlogPost> findAndUpdatePost(@PathVariable int id, @RequestBody BlogPostPayload payload) {
//        try {
//            BlogPost updatedPost = blogPostService.findAndUpdate(id, payload);
//            return ResponseEntity.ok(updatedPost);
//        } catch (NotFoundException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }


        //DELETE /blogPosts /123 => cancella lo specifico blog post
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deletePost(@PathVariable int id) {
//        try {
//            blogPostService.deletePost(id);
//        } catch (NotFoundException e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "BlogPost not found with ID: " + id);
//        }
//    }


    }

