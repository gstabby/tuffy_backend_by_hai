package com.markerhub.controller;

import cn.hutool.json.JSONUtil;
import com.markerhub.entity.ChatMsg;
import com.markerhub.entity.Message;
import com.markerhub.entity.User;
import com.markerhub.service.MessageService;
import com.markerhub.service.UserService;
import com.markerhub.shiro.JwtToken;
import com.markerhub.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.WebSocketSession;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author 86182
 */
@Controller
public class WbController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    UserService userService;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    MessageService messageService;

    @MessageMapping("/ws/chat")
    public void handleChat(String msg, WebSocketSession webSocketSession) {
        ChatMsg chatMsg = JSONUtil.toBean(msg,ChatMsg.class);
        String token = chatMsg.getToken();
        // 校验jwt
        Claims claim = jwtUtils.getClaimByToken(token);
        if(claim == null || jwtUtils.isTokenExpired(claim.getExpiration())) {
            throw new ExpiredCredentialsException("token已失效，请重新登录");
        }
        Long uid = Long.valueOf(claim.getSubject());
        chatMsg.setUId(uid);
        User user = userService.getById(uid);
        chatMsg.setUsername(user.getUsername());
        chatMsg.setAvatar(user.getAvatar());
        chatMsg.setDate(LocalDateTime.now());
        chatMsg.setToken(null);
        if(!chatMsg.getJoin()){
            Message message = new Message();
            message.setMDate(LocalDateTime.now());
            message.setMContent(chatMsg.getContent());
            message.setUId(chatMsg.getUId());
            messageService.save(message);
        }
        simpMessagingTemplate.convertAndSend("/topic/chat",chatMsg);
    }



}
