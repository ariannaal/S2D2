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

    // http://localhost:3001/blogposts / {id}
    @GetMapping("/{id}")
    private BlogPost getSinglePost(@PathVariable int id){
        return blogPostService.findById(id);
    }

    // 2. POST http://localhost:3001/blogposts (+ post)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private BlogPost createPost(@RequestBody BlogPost post){
        return blogPostService.savePost(post);
    }

    //PUT /blogPosts /123 => modifica lo specifico blog post
    @PutMapping("/{id}")
    private BlogPost findAndUpdatePost(@PathVariable int id, @RequestBody BlogPost post){
        return blogPostService.findAndUpdate(id, post);
    }

    //DELETE /blogPosts /123 => cancella lo specifico blog post
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deletePost(@PathVariable int id){
        blogPostService.deletePost(id);
    }


}
