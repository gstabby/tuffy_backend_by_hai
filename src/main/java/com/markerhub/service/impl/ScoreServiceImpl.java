package com.markerhub.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.markerhub.entity.Game;
import com.markerhub.entity.Score;
import com.markerhub.entity.User;
import com.markerhub.mapper.GameMapper;
import com.markerhub.mapper.ScoreMapper;
import com.markerhub.mapper.UserMapper;
import com.markerhub.service.ScoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.rmi.MarshalledObject;
import java.util.*;

/**
 * <p>
 * 游戏分数 服务实现类
 * </p>
 *
 * @author hai
 * @since 2020-09-14
 */
@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements ScoreService {

    @Autowired
    GameMapper gameMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public List<Map<String, Object>> getAllSort() {
        List<Game>  games = gameMapper.selectList(new QueryWrapper<>());
        List list = new ArrayList();
        Map<String,Object> map = new HashMap<>();
        if(games!=null){
            for (int i = 0; i < games.size(); i++) {
                Game game =  games.get(i);
                QueryWrapper<Score> scoreQueryWrapper = new QueryWrapper<>();
                scoreQueryWrapper.select("s_score","u_id")
                        .eq("g_id",game.getGid())
                        .orderByDesc("s_score")
                        .last("limit 0,10");
                List<Map<String,Object>> scores = this.baseMapper.selectMaps(scoreQueryWrapper);
                if(scores!=null){
                    for (int j = 0; j < scores.size(); j++) {
                        Map<String, Object> scoreMap =  scores.get(j);
                        User user = userMapper.selectById((Long)scoreMap.get("u_id"));
                        if(null!= user){
                            scoreMap.put("username",user.getUsername());
                        }else{
                            scoreMap.put("username",null);
                        }
                    }
                    map.put("Score",scores);
                }else{
                    map.put("Score",null);
                }
                map.put("GameId",game.getGid());
                map.put("GameName",game.getGName());
                list.add(map);
            }
            return list;
        }else{
            return null;
        }
    }

    @Override
    public Integer getScoreAt(Integer gId, Integer score) {
        QueryWrapper<Score> scoreQueryWrapper = new QueryWrapper<>();
        scoreQueryWrapper.select("s_score")
                .eq("g_id",gId)
                .gt("s_score",score);
        Integer at = this.baseMapper.selectCount(scoreQueryWrapper);
        return at;
    }

    @Override
    public List<Map<String, Object>> getScoreByUser(Long uid) {
        QueryWrapper<Score> scoreQueryWrapper = new QueryWrapper<>();
        scoreQueryWrapper.select("distinct g_id")
                .eq("u_id",uid);
        List<Map<String,Object>> gameList = this.baseMapper.selectMaps(scoreQueryWrapper);
        if(gameList!=null&&gameList.size()>0){
            for (int i = 0; i < gameList.size(); i++) {
                Map<String, Object> map =  gameList.get(i);
                Integer gameId = (Integer)map.get("g_id");
                map.put("gameId",gameId);
                QueryWrapper<Score> scoreQuery = new QueryWrapper<>();
                scoreQuery.select("s_score").eq("g_id",gameId);
                List<Object> scoreList = this.baseMapper.selectObjs(scoreQuery);
                map.put("scores",scoreList);
                Game game = gameMapper.selectById(gameId);
                map.put("gameName",game.getGName());
                User user = userMapper.selectById(uid);
                map.put("username",user.getUsername());
                map.put("userId",uid);
            }
            return gameList;
        }else{
            return null;
        }
    }

}
