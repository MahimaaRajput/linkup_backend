package com.socialmedia.service;

import com.socialmedia.model.User;
import com.socialmedia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplement implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserById(Integer id) throws Exception {
        Optional<User> finduser = userRepository.findById(id);
        if(finduser.isPresent()) {
            return finduser.get();
        } else {
            throw new Exception("user not exist with id "+id);
        }
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws Exception {
        return userRepository.findByEmail(email);
    }

    @Override
    public User followUser(Integer followerId, Integer followingId) throws Exception {
        User follower1=findUserById(followerId);
        User following1=findUserById(followingId);
//        null check & initialization
        if (following1.getFollowers() ==null)
            following1.setFollowers(new ArrayList<>());
        if (follower1.getFollowing() == null)
            follower1.setFollowing(new ArrayList<>());
//        add to each other's list
        following1.getFollowers().add(follower1.getId());
        follower1.getFollowing().add(following1.getId());
//        save both users
        userRepository.save(follower1);
        userRepository.save(following1);
        return follower1;
    }

    @Override
    public User updateUser(Integer myid,User updateduser) throws Exception {
        Optional<User> findUser = userRepository.findById(myid);
        if (findUser.isEmpty()) {
            throw new Exception("user not found with id " + myid);
        }
        User existingUser = findUser.get();
        if (updateduser.getEmail() != null)
            existingUser.setEmail(updateduser.getEmail());//which comes from postman
        if (updateduser.getFirstName() != null)
            existingUser.setFirstName(updateduser.getFirstName());
        if (updateduser.getLastName() != null)
            existingUser.setLastName(updateduser.getLastName());
        if (updateduser.getPassword() != null) {
            existingUser.setPassword(updateduser.getPassword());
        }
        return userRepository.save(existingUser);
    }

    @Override
    public List<User> searchUser(String query) {
        return List.of();
    }
}
