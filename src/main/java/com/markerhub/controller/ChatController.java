package com.markerhub.controller;


import cn.hutool.core.util.StrUtil;
import com.markerhub.common.lang.Result;
import com.markerhub.entity.Message;
import com.markerhub.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * <p>
 * 聊天信息 前端控制器
 * </p>
 *
 * @author hai
 * @since 2020-09-14
 */
@RestController
@RequestMapping("/chat")
public class ChatController {

}
