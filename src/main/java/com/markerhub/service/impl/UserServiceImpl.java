package com.markerhub.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.markerhub.entity.Comment;
import com.markerhub.entity.Role;
import com.markerhub.entity.User;
import com.markerhub.entity.dto.UpdatePasswordDto;
import com.markerhub.entity.dto.UserDto;
import com.markerhub.mapper.RoleMapper;
import com.markerhub.mapper.UserMapper;
import com.markerhub.service.RoleService;
import com.markerhub.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.markerhub.util.AvatarGenarator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hai
 * @since 2020-05-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    RoleService roleService;

    @Override
    public List<Map<String, Object>> getAllUserAndRole() {
        List<User> users = this.baseMapper.selectList(new QueryWrapper<>());
        List<Map<String,Object>> list = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            Map map = new HashMap();
            User user =  users.get(i);
            Role role = roleService.getById(user.getRoleId());
            map.put("user",user);
            map.put("role",role);
            list.add(map);
        }
        return list;
    }

    @Override
    public int register(UserDto userDto) {
        User user = new User();
        String path = AvatarGenarator.saveAndGetPath();
        user.setPassword(SecureUtil.md5(userDto.getPassword()));
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setCreated(LocalDateTime.now());
        user.setRoleId(2L);
        user.setAvatar(path);
        return this.baseMapper.insert(user);
    }

    @Override
    public int countByUsername(String userName) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",userName);
        return this.baseMapper.selectCount(queryWrapper);
    }

    @Override
    public Boolean updatePassword(UpdatePasswordDto updatePasswordDto, Long userId) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("password",SecureUtil.md5(updatePasswordDto.getNewPassword())).eq("id",userId).eq("password",SecureUtil.md5(updatePasswordDto.getOldPassword()));
        return this.update(updateWrapper);
    }


}
