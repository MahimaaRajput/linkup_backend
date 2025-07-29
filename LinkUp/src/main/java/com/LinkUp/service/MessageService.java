package com.LinkUp.service;

import com.LinkUp.model.Message;
import com.LinkUp.model.User;

import java.util.List;

public interface MessageService {
    Message createMessage(User user,Integer chatId,Message req) throws Exception;
    List<Message> findChatMessages(User user,Integer chatId) throws Exception;
}
