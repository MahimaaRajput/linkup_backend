package com.LinkUp.service;

import com.LinkUp.Exceptions.PostException;
import com.LinkUp.Exceptions.UserException;
import com.LinkUp.model.Post;

import java.util.List;

public interface PostService {
    Post createPost(Post post,Integer userId) throws PostException, UserException;
    String deletePost(Integer postId,Integer userId) throws Exception;
    List<Post> findPostByUserId(Integer userId);
    Post findPostById(Integer postId) throws PostException;
    List<Post> findAllPost();
    Post savedPost(Integer postId,Integer userId) throws PostException, UserException;
    Post likedPost(Integer postId,Integer userId) throws PostException, UserException;

}
