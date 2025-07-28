package com.LinkUp.repository;

import com.LinkUp.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoryRepository extends JpaRepository<Story,Integer> {
    List<Story> findByUserId(Integer userId);
}
