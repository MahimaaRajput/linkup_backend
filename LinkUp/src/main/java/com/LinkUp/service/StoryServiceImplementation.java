package com.LinkUp.service;

import com.LinkUp.model.Story;
import com.LinkUp.model.User;
import com.LinkUp.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StoryServiceImplementation implements StoryService{
    @Autowired
    StoryRepository storyRepository;
    @Autowired
    UserService userService;
    @Override
    public Story createStory(Story story, User user) {
        story.setCaption(story.getCaption());
        story.setImage(story.getImage());
        story.setUser(user);
        story.setTimestamp(LocalDateTime.now());
        return storyRepository.save(story);
    }

    @Override
    public List<Story> findUsersStory(Integer userId) throws Exception {
        User user=userService.findUserById(userId);
        if(user!=null) {
            return storyRepository.findByUserId(userId);
        }
        else{
            throw  new Exception("user not found with id "+userId);

        }

    }

    @Override
    public String deleteStory(User user, Integer storyId)throws Exception {
        Story story = storyRepository.findById(storyId)
                .orElseThrow(() -> new RuntimeException("Story not found with id " + storyId));

        if (!story.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You are not authorized to delete this story.");
        }

        storyRepository.delete(story);
        return "Story deleted with id " + storyId;
    }

}
