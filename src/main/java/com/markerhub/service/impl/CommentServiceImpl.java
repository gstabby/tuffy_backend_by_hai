package com.markerhub.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.markerhub.entity.Blog;
import com.markerhub.entity.Comment;
import com.markerhub.entity.dto.CommentDto;
import com.markerhub.mapper.BlogMapper;
import com.markerhub.mapper.CommentMapper;
import com.markerhub.mapper.UserMapper;
import com.markerhub.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.markerhub.util.ShiroUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author hai
 * @since 2020-09-03
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public void addComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setCCid(commentDto.getCCid());
        comment.setBId(commentDto.getBId());
        comment.setContent(commentDto.getContent());
        comment.setOwnerId(ShiroUtil.getProfile().getId().intValue());
        comment.setTargeId(commentDto.getTargeId());
        comment.setContent(commentDto.getContent());
        comment.setTime(LocalDateTime.now());
        this.baseMapper.insert(comment);
    }

    @Override
    public List<Map<String, Object>> selectCommnetByBlog(Long bid) {
        return this.baseMapper.selectCommnetByBlog(bid);
    }

    @Override
    public List<Map<String,Object>> getByIsRead(Boolean isRead, Long target) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        Integer read = 0;
        if(isRead){
            read = 1;
        }
        queryWrapper.eq("targe_id",target).eq("is_read",read).orderByDesc("time");
        List<Map<String,Object>> comments =  this.listMaps(queryWrapper);
        for (int i = 0; i < comments.size(); i++) {
            Map<String, Object> map =  comments.get(i);
            Long blogid = (Long) map.get("b_id");
            Integer targetId = (Integer) map.get("targe_id");
            QueryWrapper<Blog> query = new QueryWrapper<>();
            query.eq("id",blogid).select("title");
            map.put("blogTitle",(blogMapper.selectOne(query)).getTitle());
            map.put("targetName",userMapper.selectById(targetId).getUsername());
        }
        return comments;
    }

    @Override
    public boolean read(Long blogId, Long target) {
        UpdateWrapper<Comment> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("is_read",1).eq("b_id",blogId).eq("targe_id",target);
        return this.update(updateWrapper);
    }
}
