package com.LinkUp.controller;

import com.LinkUp.model.Post;
import com.LinkUp.model.User;
import com.LinkUp.response.ApiResponse;
import com.LinkUp.service.PostService;
import com.LinkUp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("posts")
public class PostController {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @PostMapping("/api/create")
    public ResponseEntity<Post> createPost(@RequestHeader("Authorization")String jwt,@RequestBody Post post) throws Exception {
        try {
            User reqUser=userService.findUserByJwt(jwt);
            Post createdPost = postService.createPost(post,reqUser.getId());
            return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
        }catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("api/delete/postid/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId,@RequestHeader("Authorization")String jwt )
    {
        try
        {
            User reqUser=userService.findUserByJwt(jwt);
            String msg= postService.deletePost(postId,reqUser.getId());
            ApiResponse res =new ApiResponse(msg,true);
            return new ResponseEntity<>(res,HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse res = new ApiResponse(e.getMessage(), false);
            return new ResponseEntity<>(res,HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("api/userid/{userId}")
    public ResponseEntity<List<Post>> findUsersPost(@PathVariable Integer userId)
    {
        List<Post> posts=postService.findPostByUserId(userId);
        return new ResponseEntity<>(posts,HttpStatus.FOUND);
    }
    @GetMapping("api/postid/{postId}")
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
    @GetMapping("api/all")
    public ResponseEntity<List<Post>> findAllPost()
    {
        List<Post> posts= postService.findAllPost();
        return new  ResponseEntity<>(posts,HttpStatus.FOUND);
    }
    @PutMapping("api/save/postid/{postId}")
    public ResponseEntity<Post> savedPost(@PathVariable Integer postId, @RequestHeader("Authorization")String jwt)
    {
        try
        {
            User reqUser=userService.findUserByJwt(jwt);
            Post posts=postService.savedPost(postId,reqUser.getId());
            return new ResponseEntity<>(posts,HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("api/like/postid/{postId}")
    public ResponseEntity<Post> likedPost(@PathVariable Integer postId,@RequestHeader("Authorization")String jwt) throws Exception {


            User reqUser=userService.findUserByJwt(jwt);
            Post posts=postService.likedPost(postId,reqUser.getId());
            return new ResponseEntity<>(posts,HttpStatus.OK);

    }




}
