package com.markerhub.service;

import com.markerhub.entity.Score;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 游戏分数 服务类
 * </p>
 *
 * @author hai
 * @since 2020-09-14
 */
public interface ScoreService extends IService<Score> {

    /**
     * 获取所有游戏排名情况
     * @return
     */
    public List<Map<String,Object>> getAllSort();

    /**
     * 查询分数的排名情况
     * @return
     */
    public Integer getScoreAt(Integer gId, Integer score);

    /**
     * 获取用户游戏分数信息
     * @param uid
     * @return
     */
    List<Map<String,Object>>getScoreByUser(Long uid);
}
