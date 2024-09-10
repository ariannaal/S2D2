package com.example.S2D2.services;

import com.example.S2D2.entities.BlogPost;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class BlogPostsService {

    private List<BlogPost> blogPostsList = new ArrayList<>();

    // ritorna tutti i post
    public List<BlogPost> getAllBlogPosts() {
        return this.blogPostsList;
    }

    // salva i post
    public BlogPost savePost(BlogPost post) {
        Random rndm = new Random();
        post.setId(rndm.nextInt(1, 10000));
        this.blogPostsList.add(post);
        return post;
    }

    // ritorna il post con un certo id
    public BlogPost findById(int blogPostId) {
        for (BlogPost post : blogPostsList) {
            if (post.getId() == blogPostId) {
                return post;
            }
        }
        return null;
    }

    // modifica lo specifico blog post
    public BlogPost findAndUpdate(int id, BlogPost newPost) {
        for (BlogPost post : blogPostsList) {
            if (post.getId() == id) {

                post.setContenuto(newPost.getContenuto());

                return post;
            }
        }
        return null;
    }


}
