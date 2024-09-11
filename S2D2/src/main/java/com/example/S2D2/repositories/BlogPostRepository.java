package com.example.S2D2.repositories;

import com.example.S2D2.entities.Autore;
import com.example.S2D2.entities.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlogPostRepository  extends JpaRepository<BlogPost, Integer> {

    Optional<BlogPost> findById(int id);

}
