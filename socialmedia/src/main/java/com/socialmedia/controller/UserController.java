package com.socialmedia.controller;

import com.socialmedia.model.User;
import com.socialmedia.repository.UserRepository;
import com.socialmedia.service.UserService;
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
    @Autowired
    private UserService userService;

    @PostMapping("user")
    public User createUser(@RequestBody User thisuser)
    {
        return userService.registerUser(thisuser);
    }

    @GetMapping("allusers")
    public List<User> getusers()
    {
        return userRepository.findAll();
    }
    @GetMapping("user/id/{myid}")
    public User getUserbyId(@PathVariable Integer myid) throws Exception {
       return userService.findUserById(myid);
    }
    @GetMapping("user/email/{email}")
    public Optional<User> getUserByEmail(@PathVariable String email) throws Exception {
        return userService.findUserByEmail(email);
    }

    @PutMapping("user/id/{myid}")
    public User updateUser(@PathVariable int myid,@RequestBody User updateduser) throws Exception {

        return userService.updateUser(myid,updateduser);
    }

    @DeleteMapping("user/id/{myid}")
    public String deleteUser(@PathVariable int myid) throws Exception
    {
        Optional<User> findUser =userRepository.findById(myid);
        if(findUser.isEmpty())
        {
            throw new Exception("user not found for id "+myid);
        }
         userRepository.delete(findUser.get()); //delete

        return "user deleted with id "+myid;
    }
    }


