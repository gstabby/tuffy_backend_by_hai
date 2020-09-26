package com.markerhub.controller;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.markerhub.common.lang.Result;
import com.markerhub.entity.User;
import com.markerhub.entity.dto.ForgetPassDto;
import com.markerhub.entity.dto.UpdatePasswordDto;
import com.markerhub.entity.dto.UpdateUserDto;
import com.markerhub.entity.dto.UserDto;
import com.markerhub.service.UserService;
import com.markerhub.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hai
 * @since 2020-05-25
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    EmailUtil emailUtil;

    @Value("${send-email-wait}")
    Integer wait;

    @Value("${emailconfig.email.from}")
    String emailFrom;

    @GetMapping("/index")
    public void index() {
        AvatarGenarator.saveAndGetPath();
    }

    @ApiOperation("所有用户信息接口(管理员角色)")
    @RequiresRoles("vip")
    @GetMapping("/getAllUserAndRole")
    public Result getAllUserAndRole(){
        return Result.succ(userService.getAllUserAndRole());
    }

    @PostMapping("/register")
    public Result register(@Valid UserDto userDto)  {
         Map data = (Map) EhCacheUtil.get("myCache",userDto.getEmail());
        if (null==data){
            return Result.fail("验证码失效");
        }

        if(!((String)data.get("code")).equalsIgnoreCase(userDto.getCode())){
            return Result.fail("验证码错误");
        }
        if (userService.countByUsername(userDto.getUsername())>0){
            return Result.fail("用户名已存在");
        }
        EhCacheUtil.remove("myCache",userDto.getEmail());
        userService.register(userDto);

        return Result.succ(null);
    }

    @PostMapping("/isExistUserName")
    public Result isExistUserName(@RequestParam(name = "userName",required = true) String userName){
        if (userService.countByUsername(userName)>0){
            return Result.fail("用户名已存在");
        }else {
            return Result.succ(null);
        }
    }

    @GetMapping("/getCode")
    public  Result getCode(@Email@RequestParam(name = "email",required = true)String email){
        Map map = (Map) EhCacheUtil.get("myCache",email);
        if (null!=map){
            Long lastTime = (Long) map.get("time");
            Long pass =  System.currentTimeMillis() - lastTime;
            long time = wait - pass/1000;
            if(time>0){
                return Result.fail("每一分钟获取一次,还有"+time+"秒");
            }
        }
        String code = RandomFourNumUtils.getRandomFourNum();
        String string = emailUtil.send(CollUtil.newArrayList(email,emailFrom),"火影忍者牛逼","我喜欢看那鲁多和杀死及:"+code);
        Map data = new HashMap<>(2);
        data.put("code",code);
        data.put("time", System.currentTimeMillis());
        EhCacheUtil.put("myCache",email,data);
        return Result.succ(null);
    }

    @RequiresAuthentication
    @PostMapping("/updatePassword")
    public Result updatePassword(@Valid UpdatePasswordDto updatePasswordDto ){
        Long userId = ShiroUtil.getProfile().getId().longValue();
        Boolean res =  userService.updatePassword(updatePasswordDto,userId);
        if(res){
            return Result.succ(res);
        }else{
            return Result.fail("密码错误");
        }

    }

    @GetMapping("/getUsernameByEmail")
    public Result getUsername(@Email@RequestParam("email") String email,@RequestParam("code") String code){
        Map data = (Map) EhCacheUtil.get("myCache",email);
        if (null==data){
            return Result.fail("验证码失效");
        }
        if(!((String)data.get("code")).equalsIgnoreCase(code)){
            return Result.fail("验证码错误");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("email",email).select("id","username","created","avatar");
        List<User> users = userService.list(queryWrapper);
        return Result.succ(users);
    }


    @PostMapping("/forgetPassword")
    public Result forgetPass(@Valid ForgetPassDto forgetPassDto){
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",forgetPassDto.getUsername());
        User user = userService.getOne(queryWrapper);
        Map data = (Map) EhCacheUtil.get("myCache",user.getEmail());
        if (null==data){
            return Result.fail("验证码失效");
        }
        if(!((String)data.get("code")).equalsIgnoreCase(forgetPassDto.getCode())){
            return Result.fail("验证码错误");
        }
        UpdateWrapper<User> updateWrapper = new UpdateWrapper();
        updateWrapper.set("password", SecureUtil.md5(forgetPassDto.getNewPassword())).eq("username",forgetPassDto.getUsername());
        return Result.succ(userService.update(updateWrapper));
    }

    @RequiresAuthentication
    @PostMapping("/updateUser")
    public Result updateUser(@Valid UpdateUserDto updateUserDto){
        Long userId = ShiroUtil.getProfile().getId().longValue();
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("id",userId);
        User user = userService.getOne(queryWrapper);
        if(user.getEmail().equals(updateUserDto.getEmail())){
            UpdateWrapper<User> updateWrapper = new UpdateWrapper();
            updateWrapper.set("username", updateUserDto.getUsername()).eq("id",userId);
            return Result.succ(userService.update(updateWrapper));
        }else{
            Map data = (Map) EhCacheUtil.get("myCache",updateUserDto.getEmail());
            if (null==data){
                return Result.fail("验证码失效");
            }
            if(!((String)data.get("code")).equalsIgnoreCase(updateUserDto.getCode())){
                return Result.fail("验证码错误");
            }
            UpdateWrapper<User> updateWrapper = new UpdateWrapper();
            updateWrapper.set("username", updateUserDto.getUsername()).set("email",updateUserDto.getEmail()).eq("id",userId);
            return Result.succ(userService.update(updateWrapper));
        }

    }



}
