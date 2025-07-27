package com.LinkUp.controller;

import com.LinkUp.model.Reels;
import com.LinkUp.model.User;
import com.LinkUp.service.ReelsService;
import com.LinkUp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reels")
public class ReelsController {
    @Autowired
    private ReelsService reelsService;
    @Autowired
    private UserService userService;
    @PostMapping("api/create")
    public Reels createReels(@RequestBody Reels reels, @RequestHeader("Authorization") String jwt)
    {
        User reqUser= userService.findUserByJwt(jwt);
        return reelsService.createReels(reels,reqUser);
    }
    @GetMapping("api/allreels")
    public List<Reels> findAllReels()
    {
        return reelsService.findAllReels();
    }
    @GetMapping("api/userId/{userId}")
    public List<Reels> findUsersReels(@PathVariable Integer userId) throws Exception {

        return reelsService.findUsersReel(userId);
    }
}
