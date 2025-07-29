package com.LinkUp.service;

import com.LinkUp.model.Chat;
import com.LinkUp.model.Message;
import com.LinkUp.model.User;
import com.LinkUp.repository.ChatRepository;
import com.LinkUp.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class MessageServiceImplementation implements MessageService{
    @Autowired
    MessageRepository messageRepository;
    @Autowired
   Chatservice chatservice;
    @Autowired
    ChatRepository chatRepository;
    @Override
    public Message createMessage(User user, Integer chatId, Message req) throws Exception {
        Chat chat=chatservice.fndChatById(chatId);
        Message message=new Message();
        message.setChat(chat);
        message.setUser(user);
        message.setContent(req.getContent());
        message.setImage(req.getImage());
        message.setTimestamp(LocalDateTime.now());
        Message savedMessage=messageRepository.save(message);
        chat.getMessages().add(savedMessage);
        chatRepository.save(chat);
        return savedMessage;
    }

    @Override
    public List<Message> findChatMessages(User user,Integer chatId) throws Exception {
        Chat chat=chatservice.fndChatById(chatId);
        return messageRepository.findByChatId(chatId);
    }
}
