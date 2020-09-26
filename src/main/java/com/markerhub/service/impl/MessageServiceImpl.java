package com.markerhub.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.markerhub.entity.Message;
import com.markerhub.mapper.MessageMapper;
import com.markerhub.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 玩家留言表 服务实现类
 * </p>
 *
 * @author hai
 * @since 2020-09-13
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Value("${messages-page-size}")
    Integer size;

    @Override
    public IPage<Map<String, Object>> getAllByPage(Integer current) {
        Page<Map> page = new Page<Map>(current,size);
        return this.baseMapper.getAllByPage(page);
    }
}
