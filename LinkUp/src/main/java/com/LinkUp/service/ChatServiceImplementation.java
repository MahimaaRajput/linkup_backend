package com.LinkUp.service;

import com.LinkUp.model.Chat;
import com.LinkUp.model.User;
import com.LinkUp.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImplementation implements Chatservice {
    @Autowired
    private UserService userService;
    @Autowired
    private ChatRepository chatRepository;

    @Override
    public Chat createChat(User reqUser, User user2) {
        Chat isExist=chatRepository.findChatByUserId(reqUser,user2);
        if(isExist!=null)
        {
            return isExist;
        }
        Chat chat=new Chat();
        chat.getUser().add(user2);
        chat.getUser().add(reqUser);
        chat.setChatName(reqUser.getFirstName()+"& " +user2.getFirstName());
        chat.setTimeStamp(LocalDateTime.now());
        return chatRepository.save(chat);

    }

    @Override
    public Chat fndChatById(Integer chatId) throws Exception {
         Optional<Chat> opt=chatRepository.findById(chatId);
         if(opt.isEmpty())
         {
             throw new Exception("chat not found by id " +chatId);
         }
         return opt.get();
    }

    @Override
    public List<Chat> findUsersChat(Integer userId) {
        return chatRepository.findByUserId(userId);
    }
}
