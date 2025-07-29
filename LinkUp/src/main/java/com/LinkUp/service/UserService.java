package com.LinkUp.service;

import com.LinkUp.Exceptions.UserException;
import com.LinkUp.model.User;

import java.util.List;

public interface UserService {
    User registerUser(User user);

    User findUserById(Integer id) throws UserException;

    User findUserByEmail(String email) throws UserException;

    User followUser(Integer followerId, Integer followingId) throws UserException;

    User updateUser(Integer id, User user) throws UserException;

    List<User> searchUser(String query);

    User findUserByJwt(String jwt);

    String deleteUser(Integer id) throws UserException;

}
