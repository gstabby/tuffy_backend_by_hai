package com.markerhub.service.impl;

import com.markerhub.entity.Game;
import com.markerhub.mapper.GameMapper;
import com.markerhub.service.GameService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 游戏类别 服务实现类
 * </p>
 *
 * @author hai
 * @since 2020-09-14
 */
@Service
public class GameServiceImpl extends ServiceImpl<GameMapper, Game> implements GameService {

}
