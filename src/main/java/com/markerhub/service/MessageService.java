package com.markerhub.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.markerhub.entity.Message;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 玩家留言表 服务类
 * </p>
 *
 * @author hai
 * @since 2020-09-13
 */
public interface MessageService extends IService<Message> {

    /**
     * 获取留言通过分页
     * @param current
     * @return
     */
    IPage<Map<String, Object>> getAllByPage(Integer current);
}
