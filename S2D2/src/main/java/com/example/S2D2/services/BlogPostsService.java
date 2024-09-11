package com.example.S2D2.services;

import com.example.S2D2.entities.Autore;
import com.example.S2D2.entities.BlogPost;
import com.example.S2D2.entities.BlogPostPayload;
import com.example.S2D2.exceptions.NotFoundException;
import com.example.S2D2.repositories.AuthorRepository;
import com.example.S2D2.repositories.BlogPostRepository;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Service
public class BlogPostsService {
    @Autowired
    private BlogPostRepository blogRepository;

    @Autowired
    private AuthorRepository authorRepository;

    // ritorna tutti i post
    public List<BlogPost> getAllBlogPosts() {
        return this.blogRepository.findAll();
    }

    // salva i post
    public BlogPost savePost(BlogPostPayload payload) {

        Autore author = authorRepository.findById(payload.getAuthor_id())
                .orElseThrow(() -> new NotFoundException("Author not found with ID: " + payload.getAuthor_id()));

        BlogPost newBlogPost = new BlogPost(
                payload.getCategoria(),
                payload.getTitolo(),
                payload.getCover(),
                payload.getContenuto(),
                payload.getTempoDiLettura(),
                author
        );

        return blogRepository.save(newBlogPost);
    }

    public BlogPost findById(int blogPostId) {
        return this.blogRepository.findById(blogPostId)
                .orElseThrow(() -> new NotFoundException("BlogPost not found with ID: " + blogPostId));
    }

        // modifica lo specifico blog post
        public BlogPost findAndUpdate(int id, BlogPostPayload payload) {
            Optional<BlogPost> existingPostOpt = blogRepository.findById(id);
            if (existingPostOpt.isPresent()) {
                BlogPost existingPost = existingPostOpt.get();

                existingPost.setCategoria(payload.getCategoria());
                existingPost.setTitolo(payload.getTitolo());
                existingPost.setCover(payload.getCover());
                existingPost.setContenuto(payload.getContenuto());
                existingPost.setTempoDiLettura(payload.getTempoDiLettura());

                Autore author = authorRepository.findById(payload.getAuthor_id())
                        .orElseThrow(() -> new NotFoundException("Author not found with ID: " + payload.getAuthor_id()));

                existingPost.setAutore(author);

                return blogRepository.save(existingPost);
            } else {
                throw new NotFoundException("BlogPost not found with ID: " + id);
            }
        }


        // elimina lo specifico post
        public void deletePost(int id) {
            if (!blogRepository.existsById(id)) {
                throw new NotFoundException("BlogPost not found with ID: " + id);
            }
            blogRepository.deleteById(id);
        }

        // Trova tutti i post con paginazione e ordinamento
        public Page<BlogPost> findAll ( int page, int size, String sortBy){
            if (page < 0) {
                page = 0;
            }
            if (size <= 0) {
                size = 10;
            }
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
            return blogRepository.findAll(pageable);
        }
    }

