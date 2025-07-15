package com.socialmedia.controller;

import com.socialmedia.model.Post;
import com.socialmedia.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("posts")
public class PostController {
    @Autowired
    private PostService postService;
    @PostMapping("create/id/{userId}")
    public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable Integer userId) throws Exception {
        Post createdPost =postService.createPost(post,userId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }
}
