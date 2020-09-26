package com.markerhub.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.markerhub.common.lang.Result;
import com.markerhub.entity.Message;
import com.markerhub.service.MessageService;
import com.markerhub.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 玩家留言表 前端控制器
 * </p>
 *
 * @author hai
 * @since 2020-09-13
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @GetMapping(value = "/allByPage")
    public Result allByPage(@RequestParam(value = "currentPage",defaultValue = "1") Integer currentPage){
        IPage<Map<String, Object>> messageIPage = messageService.getAllByPage(currentPage);
        return Result.succ(messageIPage);
    }

    @RequiresAuthentication
    @PostMapping(value = "/save")
    public Result save(@RequestParam("content") String content){
       if(StrUtil.isBlank(content)){
           return Result.fail("留言内容不能为空");
       }
       if(content.length()>500){
           return Result.fail("留言内容过长");
       }
       Message message = new Message();
       message.setMContent(content);
       message.setMDate(LocalDateTime.now());
       message.setUId(ShiroUtil.getProfile().getId().longValue());
       return Result.succ(messageService.save(message));
    }

    @RequiresAuthentication
    @PostMapping(value = "/delete")
    public Result deleteById(@RequestParam(name  = "mId") Integer mId){
        Long Uid = ShiroUtil.getProfile().getId().longValue();
        Message message = messageService.getById(mId);
        if(null==message){
            return Result.succ("留言不存在或已被删除");
        }
        if(!message.getUId().equals(Uid)){
            return Result.fail("不是您的留言无法删除");
        }
        messageService.getById(mId);
        return Result.succ("删除成功");
    }
}
