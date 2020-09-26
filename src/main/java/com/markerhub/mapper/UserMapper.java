package com.markerhub.mapper;

import com.markerhub.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hai
 * @since 2020-05-25
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

}
