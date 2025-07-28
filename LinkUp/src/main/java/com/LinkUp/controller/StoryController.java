package com.LinkUp.controller;

import com.LinkUp.model.Story;
import com.LinkUp.model.User;
import com.LinkUp.service.StoryService;
import com.LinkUp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("story")
public class StoryController {
    @Autowired
    private StoryService storyService;
    @Autowired
    private UserService userService;
    @PostMapping("api/create")
    public Story createStory(@RequestBody Story story, @RequestHeader("Authorization")String jwt){
        User reqUser=userService.findUserByJwt(jwt);
        return storyService.createStory(story,reqUser);
    }
    @GetMapping("api/userId/{userId}")
    public List<Story> findUsersStory(@PathVariable Integer userId) throws Exception {
        return storyService.findUsersStory(userId);
    }
    @DeleteMapping("api/delete/storyId/{storyId}")
    public String deleteStory(@RequestHeader("Authorization")String jwt,@PathVariable Integer storyId) throws Exception {
        User reqUser=userService.findUserByJwt(jwt);
        storyService.deleteStory(reqUser,storyId);
        return "story deleted";
    }


}
