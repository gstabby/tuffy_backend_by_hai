package com.markerhub.service;

import com.markerhub.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.markerhub.entity.dto.CommentDto;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author hai
 * @since 2020-09-03
 */
public interface CommentService extends IService<Comment> {

    /**
     * 添加评论
     * @param commentDto
     */
    void addComment(CommentDto commentDto);

    /**
     * 根据博客id 查评论
     * @param bid
     * @return
     */
    List<Map<String,Object>> selectCommnetByBlog(Long bid);

    /**
     * 根据读取情况获取用户收到的评论
     * @param isRead
     * @param target
     * @return
     */
    List<Map<String,Object>> getByIsRead(Boolean isRead, Long target);

    /**
     * 阅读评论
     * @param blogId
     * @param target
     * @return
     */
    boolean read(Long blogId, Long target);
}
