package com.LinkUp.service;

import com.LinkUp.Exceptions.ChatException;
import com.LinkUp.model.Chat;
import com.LinkUp.model.User;

import java.util.List;

public interface Chatservice {
    Chat createChat(User reqUser,User user2);
    Chat fndChatById(Integer chatId) throws ChatException;
    List<Chat> findUsersChat(Integer userId);
}
