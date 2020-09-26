package com.markerhub.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.markerhub.entity.Blog;
import com.markerhub.entity.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 玩家留言表 Mapper 接口
 * </p>
 *
 * @author hai
 * @since 2020-09-13
 */
public interface MessageMapper extends BaseMapper<Message> {

    /**
     * 分页获取留言
     * @param page
     * @return
     */
    IPage<Map<String,Object>> getAllByPage(Page<Map> page);
}
