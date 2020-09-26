package com.markerhub.controller;


import com.markerhub.common.lang.Result;
import com.markerhub.entity.dto.CommentDto;
import com.markerhub.entity.dto.UserDto;
import com.markerhub.service.CommentService;
import com.markerhub.util.EhCacheUtil;
import com.markerhub.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author hai
 * @since 2020-09-03
 */

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @Value("${comment-wait}")
    Integer wait;

    @RequiresAuthentication
    @PostMapping("/add")
    public Result addComment(@Validated CommentDto commentDto){
        Long userId = ShiroUtil.getProfile().getId().longValue();
        Map map = (Map) EhCacheUtil.get("commentCache",userId.toString());
        if (null!=map){
            Long lastTime = (Long) map.get("time");
            Long pass =  System.currentTimeMillis() - lastTime;
            long time = wait - pass/1000;
            if(time>0){
                return Result.fail("您的评论CD："+time+"秒");
            }
        }
        commentService.addComment(commentDto);
        Map data = new HashMap<>(2);
        data.put("isComment",true);
        data.put("time", System.currentTimeMillis());
        EhCacheUtil.put("commentCache",userId.toString(),data);
        return Result.succ(null);
    }


    @GetMapping("/getCommentByBlog")
    public Result getCommentByBlog(@RequestParam("bid") Long bid){
        return Result.succ(commentService.selectCommnetByBlog(bid));
    }

    @RequiresAuthentication
    @PostMapping("/deleteByCid")
    public Result deleteByCid(@RequestParam("cid") Long cid){
        commentService.removeById(cid);
        return Result.succ(null);
    }

    @RequiresAuthentication
    @GetMapping("/getByIsRead")
    public Result getByIsRead(@RequestParam(value = "isRead") Boolean isRead){
        Long target = ShiroUtil.getProfile().getId().longValue();
        return Result.succ(commentService.getByIsRead(isRead,target));
    }

    @RequiresAuthentication
    @PostMapping("/read")
    public Result read(@RequestParam(value = "blogId") Long blogId){
        Long target = ShiroUtil.getProfile().getId().longValue();
        return Result.succ(commentService.read(blogId,target));
    }

}
