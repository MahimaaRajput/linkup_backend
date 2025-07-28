package com.LinkUp.repository;

import com.LinkUp.model.Chat;
import com.LinkUp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface ChatRepository extends JpaRepository<Chat,Integer> {
    List<Chat> findByUserId(Integer userId);
    @Query("SELECT c FROM Chat c WHERE :user MEMBER OF c.user AND :reqUser MEMBER OF c.user")
    Chat findChatByUserId(@Param("user") User user, @Param("reqUser") User reqUser);

}
