package com.LinkUp.controller;

import com.LinkUp.model.User;
import com.LinkUp.repository.UserRepository;
import com.LinkUp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping()
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

    @GetMapping("/api/allusers")
    public List<User> getusers()
    {
        return userRepository.findAll();
    }
    @GetMapping("/api/user/id/{myid}")
    public User getUserbyId(@PathVariable Integer myid) throws Exception {
       return userService.findUserById(myid);
    }
    @GetMapping("/api/user/email/{email}")
    public Optional<User> getUserByEmail(@PathVariable String email) throws Exception {
        return userService.findUserByEmail(email);
    }

    @PutMapping("/api/user/id/{myid}")
    public User updateUser(@PathVariable int myid,@RequestBody User updateduser) throws Exception {

        return userService.updateUser(myid,updateduser);
    }
    @PutMapping("user/follow/{followerId}/{followingId}")
    public User followUser(@PathVariable Integer followerId,@PathVariable Integer followingId) throws Exception {
        return userService.followUser(followerId,followingId);
    }
    @GetMapping("user/search")
    public List<User> searchUser(@RequestParam("query") String query) {
        return userService.searchUser(query);
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


