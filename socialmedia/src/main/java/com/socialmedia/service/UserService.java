package com.socialmedia.service;

import com.socialmedia.model.User;

public interface UserService {
    User registerUser(User user);
    User findUserById(Integer id) throws Exception;
}
