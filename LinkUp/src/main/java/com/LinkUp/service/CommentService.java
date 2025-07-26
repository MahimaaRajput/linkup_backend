package com.LinkUp.service;

import com.LinkUp.model.Comment;
import com.LinkUp.model.User;

public interface CommentService {
    Comment createComment(Comment comment, Integer postId, Integer userid) throws Exception;
    Comment likeComment(Integer commentId,Integer userId) throws Exception;
    Comment findCommentById(Integer commentId) throws Exception;
}
