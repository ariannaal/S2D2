package com.example.S2D2.controllers;

import com.example.S2D2.entities.BlogPost;
import com.example.S2D2.entities.BlogPostPayload;
import com.example.S2D2.exceptions.NotFoundException;
import com.example.S2D2.services.BlogPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/blogposts")
public class BlogPostController {

    @Autowired
    private BlogPostsService blogPostService;

    // http://localhost:3001/blogposts
    @GetMapping
    public List<BlogPost> getAllBlogPosts() {
        return blogPostService.getAllBlogPosts();
    }

    // http://localhost:3001/blogposts / {id}
//    @GetMapping("/{id}")
//    private BlogPost getSinglePost(@PathVariable int id){
//        return blogPostService.findById(id);
//    }

    // 2. POST http://localhost:3001/blogposts (+ post)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost createPost(@RequestBody BlogPostPayload payload) {
        return blogPostService.savePost(payload);
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
