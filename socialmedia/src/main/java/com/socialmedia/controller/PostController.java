package com.socialmedia.controller;

import com.socialmedia.model.Post;
import com.socialmedia.response.ApiResponse;
import com.socialmedia.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("posts")
public class PostController {
    @Autowired
    private PostService postService;
    @PostMapping("create/id/{userId}")
    public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable Integer userId) throws Exception {
        try {
            Post createdPost = postService.createPost(post, userId);
            return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
        }catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/id/{postId}/{userId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId, @PathVariable Integer userId)
    {
        try
        {
            String msg= postService.deletePost(postId,userId);
            ApiResponse res =new ApiResponse(msg,true);
            return new ResponseEntity<>(res,HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse res = new ApiResponse(e.getMessage(), false);
            return new ResponseEntity<>(res,HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("userid/{userId}")
    public ResponseEntity<List<Post>> findUsersPost(@PathVariable Integer userId)
    {
        List<Post> posts=postService.findPostByUserId(userId);
        return new ResponseEntity<>(posts,HttpStatus.FOUND);
    }
    @GetMapping("postid/{postId}")
    public ResponseEntity<Post> findPostById(@PathVariable Integer postId) throws Exception
    {
        try
        {
            Post posts =postService.findPostById(postId);
            return new ResponseEntity<>(posts,HttpStatus.FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("all")
    public ResponseEntity<List<Post>> findAllPost()
    {
        List<Post> posts= postService.findAllPost();
        return new  ResponseEntity<>(posts,HttpStatus.FOUND);
    }
    @PutMapping("save/postid/{postId}/user/{userId}")
    public ResponseEntity<Post> savedPost(@PathVariable Integer postId, @PathVariable Integer userId)
    {
        try
        {
            Post posts=postService.savedPost(postId,userId);
            return new ResponseEntity<>(posts,HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("like/postid/{postId}/userid/{userId}")
    public ResponseEntity<Post> likedPost(@PathVariable Integer postId,@PathVariable Integer userId)
    {
        try
        {
            Post posts=postService.likedPost(postId,userId);
            return new ResponseEntity<>(posts,HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }




}
