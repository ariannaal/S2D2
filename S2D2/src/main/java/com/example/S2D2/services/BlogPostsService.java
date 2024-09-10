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
    public List<BlogPost> getAllBlogPosts(){
        return this.blogPostsList;
    }

    // salva i post
    public BlogPost savePost(BlogPost post){
        Random rndm = new Random();
        post.setId(rndm.nextInt(1, 10000));
        this.blogPostsList.add(post);
        return post;
    }

    // ritorna il post con un certo id
    public BlogPost findById(int blogPostId){
        BlogPost found = null;
        for (BlogPost post : this.blogPostsList){
            if(post.getId() == blogPostId) found = post;
        }
        if (found == null )
            System.out.println("Nessun post trovato con questo id");
        return found;
    }

//    public User findByIdAndUpdate(int userId, User updatedUser){
//        User found = null;
//        for (User user: this.usersList){
//            if(user.getId() == userId) {
//                found = user;
//                found.setName(updatedUser.getName());
//                found.setSurname(updatedUser.getSurname());
//            }
//        }
//        if (found == null ) throw new NotFoundException(userId);
//        return found;
//    }

}
