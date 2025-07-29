package com.LinkUp.controller;

import com.LinkUp.Exceptions.ChatException;
import com.LinkUp.Exceptions.UserException;
import com.LinkUp.model.Chat;
import com.LinkUp.model.User;
import com.LinkUp.repository.ChatRepository;
import com.LinkUp.request.ChatRequest;
import com.LinkUp.service.Chatservice;
import com.LinkUp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("chat")
public class ChatController {
    @Autowired
    private Chatservice chatservice;
    @Autowired
    private UserService userService;

    @PostMapping("api/create")
    public Chat createChat(@RequestHeader("Authorization")String jwt, @RequestBody ChatRequest req) throws ChatException, UserException {
        User reqUser =userService.findUserByJwt(jwt);
        User user2=userService.findUserById(req.getUserId());
        return chatservice.createChat(reqUser,user2);
    }
    @GetMapping("api/chatId/{chatId}")
    Chat findChatByChatId(@PathVariable Integer chatId) throws Exception {
        return chatservice.fndChatById(chatId);
    }
    @GetMapping("api/usersChat")
    List<Chat> findUsersChat(@RequestHeader("Authorization")String jwt)
    {
        User reqUser=userService.findUserByJwt(jwt);
        return chatservice.findUsersChat(reqUser.getId());
    }
}
