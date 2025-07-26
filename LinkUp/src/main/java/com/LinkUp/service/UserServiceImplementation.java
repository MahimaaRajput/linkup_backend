package com.LinkUp.service;

import com.LinkUp.Config.JwtProvider;
import com.LinkUp.model.User;
import com.LinkUp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserById(Integer id) throws Exception {
        Optional<User> finduser = userRepository.findById(id);
        if (finduser.isPresent()) {
            return finduser.get();
        } else {
            throw new Exception("user not exist with id " + id);
        }
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        return userRepository.findByEmail(email);
    }

    @Override
    public User followUser(Integer reqUserId, Integer followingId) throws Exception {
        User reqUser = findUserById(reqUserId);
        User following1 = findUserById(followingId);
//        null check & initialization
        if (following1.getFollowers() == null)
            following1.setFollowers(new ArrayList<>());
        if (reqUser.getFollowing() == null)
            reqUser.setFollowing(new ArrayList<>());
//        add to each other's list
        following1.getFollowers().add(reqUser.getId());
        reqUser.getFollowing().add(following1.getId());
//        save both users
        userRepository.save(reqUser);
        userRepository.save(following1);
        return reqUser;
    }

    @Override
    public User updateUser(Integer myid, User updateduser) throws Exception {
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
        return userRepository.searchUser(query);
    }

    @Override
    public User findUserByJwt(String jwt) {
        String email = JwtProvider.getEmailFromToken(jwt);
        User user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public String deleteUser(Integer id) throws Exception {
        Optional<User> finduser=userRepository.findById(id);
        if(finduser.isPresent()) {
            userRepository.delete(finduser.get());
            return "user deleted succefully";
        } else {
            throw new Exception("user not exist with id " + id);
        }

    }
}
