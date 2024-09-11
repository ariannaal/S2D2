package com.example.S2D2.services;

import com.example.S2D2.entities.BlogPost;
import com.example.S2D2.repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogPostsService {
    @Autowired
    private BlogPostRepository blogRepository;

    // ritorna tutti i post
    public List<BlogPost> getAllBlogPosts() {
        return this.blogRepository.findAll();
    }

    // salva i post
    public BlogPost savePost(BlogPost post) {
        try {
            this.blogRepository.save(post);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }

        return post;
    }

    // ritorna il post con un certo id
    public Optional<BlogPost> findById(int blogPostId) {
      return this.blogRepository.findById(blogPostId);
    }

    // modifica lo specifico blog post
    public BlogPost findAndUpdate(int id, BlogPost newPost) {
        Optional<BlogPost> existingPostOpt = blogRepository.findById(id);
        if (existingPostOpt.isPresent()) {
            BlogPost existingPost = existingPostOpt.get();

            existingPost.setCategoria(newPost.getCategoria());
            existingPost.setTitolo(newPost.getTitolo());
            existingPost.setCover(newPost.getCover());
            existingPost.setContenuto(newPost.getContenuto());
            existingPost.setTempoDiLettura(newPost.getTempoDiLettura());
            existingPost.setAutoreId(newPost.getAutoreId());

            return blogRepository.save(existingPost);
        } else {
            // Gestisci il caso in cui il post non esiste
            return null;
        }
    }


    // elimina lo specifico post
    public void deletePost(int id) {

        this.blogRepository.deleteById(id);
    }

}
