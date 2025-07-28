package com.LinkUp.service;


import com.LinkUp.model.Story;
import com.LinkUp.model.User;

import java.util.List;

public interface StoryService {
    Story createStory(Story story, User user);
    List<Story> findUsersStory(Integer userId) throws Exception;
    String deleteStory(User user,Integer storyId) throws Exception;
}
