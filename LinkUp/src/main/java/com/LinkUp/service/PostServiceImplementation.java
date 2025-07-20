package com.LinkUp.service;

import com.LinkUp.model.Post;
import com.LinkUp.model.User;
import com.LinkUp.repository.PostRepository;
import com.LinkUp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImplementation implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Post createPost(Post post, Integer userId) throws Exception {
        User user= userService.findUserById(userId);
        post.setUser(user);
        post.setCreatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }

    @Override
    public String deletePost(Integer postId, Integer userId) throws Exception {
       Post foundpost=findPostById(postId);
       User founduser=userService.findUserById(userId);
       if (foundpost.getUser() == null || !foundpost.getUser().getId().equals(userId)) {
            throw new Exception("you are not allowed to delete");
       }
       postRepository.delete(foundpost);
       return "post deleted with id "+ postId;
    }

    @Override
    public List<Post> findPostByUserId(Integer userId) {
        return postRepository.findPostByByUserId(userId);
    }

    @Override
    public Post findPostById(Integer postId) throws Exception {
        Optional<Post> foundpost=postRepository.findById(postId);
        if(foundpost.isEmpty()) {
            throw new Exception("post not found with id"+postId);
        }
        return foundpost.get();
    }

    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Post savedPost(Integer postId, Integer userId) throws Exception {
        Post foundpost=findPostById(postId);
        User founduser=userService.findUserById(userId);
        if(founduser.getSavedPost().contains(foundpost))
        {
            founduser.getSavedPost().remove(foundpost);
        }
        else {
            founduser.getSavedPost().add(foundpost);
            userRepository.save(founduser);
        }
        return foundpost;
    }

    @Override
    public Post likedPost(Integer postId, Integer userId) throws Exception {
        Post foundpost=findPostById(postId);
        User founduser=userService.findUserById(userId);
        if(foundpost.getLiked()==null)
            foundpost.setLiked(new ArrayList<>());
        if(foundpost.getLiked().contains(founduser))
        {
            foundpost.getLiked().remove(founduser);
        }
        else {
            foundpost.getLiked().add(founduser);
        }
        return postRepository.save(foundpost);
    }
}
