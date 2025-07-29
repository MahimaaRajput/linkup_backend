package com.LinkUp.controller;

import com.LinkUp.model.Chat;
import com.LinkUp.model.Message;
import com.LinkUp.model.User;
import com.LinkUp.service.Chatservice;
import com.LinkUp.service.MessageService;
import com.LinkUp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {
    @Autowired
    UserService userService;
    @Autowired
    Chatservice chatservice;
    @Autowired
    MessageService messageService;
    @PostMapping("api/create/chatId/{chatId}")
    public Message createMessage(@RequestHeader("Authorization")String jwt,
                                 @PathVariable Integer chatId,
                                 @RequestBody Message message) throws Exception {
        User reqUser=userService.findUserByJwt(jwt);
        return messageService.createMessage(reqUser,chatId,message);
    }
    @GetMapping("api/chatMessages/chatId/{chatId}")
    public List<Message> findChatMessages(@RequestHeader("Authorization")String jwt,
                                          @PathVariable Integer chatId) throws Exception {
        User reqUser=userService.findUserByJwt(jwt);
        return messageService.findChatMessages(reqUser,chatId);
    }
}
