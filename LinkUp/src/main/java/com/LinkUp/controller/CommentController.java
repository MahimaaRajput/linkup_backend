package com.LinkUp.controller;

import com.LinkUp.model.Comment;
import com.LinkUp.model.Post;
import com.LinkUp.model.User;
import com.LinkUp.service.CommentService;
import com.LinkUp.service.PostService;
import com.LinkUp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @PostMapping("api/comment/{postid}")
    public Comment createComment(@RequestBody Comment comment,
                                 @PathVariable Integer postid,
                                 @RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        return commentService.createComment(comment, postid, reqUser.getId());
    }

    @PutMapping("api/comment/like/commentId/{commentId}")
    public Comment likeComment(@PathVariable Integer commentId,@RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser=userService.findUserByJwt(jwt);
        return commentService.likeComment(commentId,reqUser.getId());
    }

    @GetMapping("api/comment/commentId/{commentId}")
    public Comment getCommentById(@PathVariable Integer commentId) throws Exception {
        return commentService.findCommentById(commentId);

    }

}
