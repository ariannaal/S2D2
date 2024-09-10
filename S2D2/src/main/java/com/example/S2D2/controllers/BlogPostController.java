package com.example.S2D2.controllers;

import com.example.S2D2.entities.BlogPost;
import com.example.S2D2.services.BlogPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    // 2. POST http://localhost:3001/blogposts (+ post)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private BlogPost createPost(@RequestBody BlogPost post){
        return blogPostService.savePost(post);
    }
}
