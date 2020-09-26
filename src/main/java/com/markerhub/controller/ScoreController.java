package com.markerhub.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.markerhub.common.lang.Result;
import com.markerhub.entity.Game;
import com.markerhub.entity.Message;
import com.markerhub.service.GameService;
import com.markerhub.service.ScoreService;
import com.markerhub.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 游戏分数 前端控制器
 * </p>
 *
 * @author hai
 * @since 2020-09-14
 */
@RestController
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    ScoreService scoreService;


    @GetMapping(value = "/getAllSort")
    public Result getAllSort(){
        return Result.succ(scoreService.getAllSort());
    }

    @GetMapping(value = "/getScoreAt")
    public Result getScoreAt(@RequestParam("gId") Integer gId,@RequestParam("score") Integer score){
        return Result.succ(scoreService.getScoreAt(gId,score));
    }

    @GetMapping(value = "/getScoreByUser")
    public Result getScoreByUser(@RequestParam("uId") Long uId){
        //Long uid = ShiroUtil.getProfile().getId().longValue();
        return Result.succ(scoreService.getScoreByUser(uId));
    }


}
