package com.markerhub.service;

import com.markerhub.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.markerhub.entity.dto.UpdatePasswordDto;
import com.markerhub.entity.dto.UserDto;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hai
 * @since 2020-05-25
 */
public interface UserService extends IService<User> {

    /**
     * 获取所有用户信息和用户角色
     * @return
     */
    List<Map<String, Object>> getAllUserAndRole();

    /**
     * 注册用户
     * @param userDto
     * @return
     */
    int register(UserDto userDto);

    /**
     * 根据用户名获取用户数
     * @param userName
     * @return
     */
    int countByUsername(String userName);

    /**
     * 修改密码
     * @param updatePasswordDto
     * @param userId
     * @return
     */
    Boolean updatePassword(UpdatePasswordDto updatePasswordDto, Long userId);
}
