package com.LinkUp.service;

import com.LinkUp.model.Reels;
import com.LinkUp.model.User;
import com.LinkUp.repository.ReelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReelsServiceImplementation implements ReelsService {
    @Autowired
    private ReelsRepository  reelsRepository;
    @Autowired
    private UserService userService;
    @Override
    public Reels createReels(Reels reel, User user) {
        reel.setUser(user);
        reel.setTitle(reel.getTitle());
        reel.setVideo(reel.getVideo());
        return reelsRepository.save(reel);
    }

    @Override
    public List<Reels> findAllReels() {
        return reelsRepository.findAll();
    }

    @Override
    public List<Reels> findUsersReel(Integer userId) throws Exception {
        User user=userService.findUserById(userId);
        if(user==null)
        {
            throw new Exception("user not present with this id");
        }
        return reelsRepository.findByUserId(userId);
    }
}
