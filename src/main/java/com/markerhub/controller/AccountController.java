package com.markerhub.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.markerhub.common.dto.LoginDto;
import com.markerhub.common.lang.Result;
import com.markerhub.entity.User;
import com.markerhub.service.UserService;
import com.markerhub.util.JwtUtils;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author 86182
 */
@RestController
public class AccountController {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @ApiOperation("登录接口")
    @PostMapping("/login")
    public Result login(@Validated  LoginDto loginDto, HttpServletResponse response) {
        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
       if(user == null){
           return Result.fail("用户不存在或密码错误");
       }else{
           if(!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))){
               return Result.fail("用户不存在或密码错误");
           }else{
               String jwt = jwtUtils.generateToken(user.getId());

               response.setHeader("Authorization", jwt);
               response.setHeader("Access-control-Expose-Headers", "Authorization");

               return Result.succ(MapUtil.builder()
                       .put("id", user.getId())
                       .put("username", user.getUsername())
                       .put("avatar",user.getAvatar())
                       .put("created",user.getCreated())
                       .put("email",user.getEmail())
                       .put("roleId",user.getRoleId())
                       .map()
               );
           }
       }

    }

    @ApiOperation("注销接口")
    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.succ(null);
    }

    @GetMapping("/isTokenExpired")
    public Result isTokenExpired(@RequestParam("token")String token) {
        Claims claim = jwtUtils.getClaimByToken(token);
        if(claim == null || jwtUtils.isTokenExpired(claim.getExpiration())) {
            return Result.succ(true);
        }else{
            return Result.succ(false);
        }
    }

}
