package com.example.S2D2.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.S2D2.entities.Autore;
import com.example.S2D2.entities.BlogPost;
import com.example.S2D2.entities.BlogPostPayload;
import com.example.S2D2.exceptions.NotFoundException;
import com.example.S2D2.payloads.NewBlogPostDTO;
import com.example.S2D2.repositories.AuthorRepository;
import com.example.S2D2.repositories.BlogPostRepository;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BlogPostsService {
    @Autowired
    private BlogPostRepository blogRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private Cloudinary cloudinary;

    // ritorna tutti i post
    public List<BlogPost> getAllBlogPosts() {
        return this.blogRepository.findAll();
    }

    // salva i post
    public BlogPost savePost(NewBlogPostDTO payload) {

        Autore author = authorRepository.findById(payload.author_id())
                .orElseThrow(() -> new NotFoundException("Author not found with ID: " + payload.author_id()));

        BlogPost newBlogPost = new BlogPost();
        newBlogPost.setTempoDiLettura(payload.tempoDiLettura());
        newBlogPost.setContenuto(payload.contenuto());
        newBlogPost.setTitolo(payload.titolo());
        newBlogPost.setAutore(author);
        newBlogPost.setCategoria(payload.categoria());
        newBlogPost.setCover("http://picsum.photos/200/300");

        return blogRepository.save(newBlogPost);
    }

    public BlogPost findById(int blogPostId) {
        return this.blogRepository.findById(blogPostId)
                .orElseThrow(() -> new NotFoundException("BlogPost not found with ID: " + blogPostId));
    }

        // modifica lo specifico blog post
//        public BlogPost findAndUpdate(int id, BlogPostPayload payload) {
//            Optional<BlogPost> existingPostOpt = blogRepository.findById(id);
//            if (existingPostOpt.isPresent()) {
//                BlogPost existingPost = existingPostOpt.get();
//
//                existingPost.setCategoria(payload.getCategoria());
//                existingPost.setTitolo(payload.getTitolo());
//                existingPost.setCover(payload.get);
//                existingPost.setContenuto(payload.getContenuto());
//                existingPost.setTempoDiLettura(payload.getTempoDiLettura());
//
//                Autore author = authorRepository.findById(payload.getAuthor_id())
//                        .orElseThrow(() -> new NotFoundException("Author not found with ID: " + payload.getAuthor_id()));
//
//                existingPost.setAutore(author);
//
//                return blogRepository.save(existingPost);
//            } else {
//                throw new NotFoundException("BlogPost not found with ID: " + id);
//            }
//        }


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

        //upload cover
        public void uploadImage(int blogPostId, MultipartFile image) throws IOException {
            BlogPost blogPost = blogRepository.findById(blogPostId)
                    .orElseThrow(() -> new NotFoundException("Post con id " + blogPostId + " non trovato."));

            Map<String, Object> uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
            String immagineCover = uploadResult.get("url").toString();

            blogPost.setCover(immagineCover);
            blogRepository.save(blogPost);
        }
    }

