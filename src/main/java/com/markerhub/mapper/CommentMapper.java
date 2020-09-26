package com.markerhub.mapper;

import com.markerhub.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 Mapper 接口
 * </p>
 *
 * @author hai
 * @since 2020-09-03
 */
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 根据博客id 查评论
     * @param bid
     * @return
     */
    List<Map<String,Object>> selectCommnetByBlog(@Param("bid") Long bid);
}
