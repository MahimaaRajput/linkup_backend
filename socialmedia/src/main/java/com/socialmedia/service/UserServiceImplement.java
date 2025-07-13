package com.socialmedia.service;

import com.socialmedia.model.User;
import com.socialmedia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
