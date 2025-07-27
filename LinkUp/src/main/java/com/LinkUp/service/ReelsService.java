package com.LinkUp.service;

import com.LinkUp.model.Reels;
import com.LinkUp.model.User;

import java.util.List;

public interface ReelsService {
    Reels createReels(Reels reel, User user);
   List<Reels>  findAllReels();
   List<Reels> findUsersReel(Integer userId) throws Exception;
}
