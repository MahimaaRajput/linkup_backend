package com.LinkUp.service;

import com.LinkUp.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User registerUser(User user);
    User findUserById(Integer id) throws Exception;
    Optional<User> findUserByEmail(String email) throws Exception;
    User followUser(Integer followerId,Integer followingId) throws Exception;
    User updateUser(Integer id,User user) throws Exception;
    List<User> searchUser(String query);

}
