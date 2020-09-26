package com.markerhub.service.impl;

import com.markerhub.entity.Chat;
import com.markerhub.mapper.ChatMapper;
import com.markerhub.service.ChatService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 聊天信息 服务实现类
 * </p>
 *
 * @author hai
 * @since 2020-09-14
 */
@Service
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat> implements ChatService {

}
