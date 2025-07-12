package com.socialmedia.controller;

import com.socialmedia.model.User;
import com.socialmedia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("socialmedia")
public class UserController {


    @Autowired
    private UserRepository userRepository;

    @PostMapping("user")
    public User createUser(@RequestBody User thisuser)
    {
        return userRepository.save(thisuser);
    }

    @GetMapping("allusers")
    public List<User> getusers()
    {
        return userRepository.findAll();
    }
    @GetMapping("user/id/{myid}")
    public User getUserbyId(@PathVariable Integer myid) throws Exception
    {
       Optional <User> finduser = userRepository.findById(myid);
       if(finduser.isPresent()) {
           return finduser.get();
       } else {
           throw new Exception("user not exist with id "+myid);
       }
    }

    @PutMapping("user/id/{myid}")
    public User updateUser(@PathVariable int myid,@RequestBody User updateduser) throws Exception {
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

    @DeleteMapping("user/id/{myid}")
    public String deleteUser(@PathVariable int myid) throws Exception
    {
        Optional<User> findUser =userRepository.findById(myid);
        if(findUser.isEmpty())
        {
            throw new Exception("user not found for id "+myid);
        }
         userRepository.delete(findUser.get());

        return "user deleted with id "+myid;
    }
    }


