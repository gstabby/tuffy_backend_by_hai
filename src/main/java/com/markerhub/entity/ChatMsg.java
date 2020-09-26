package com.markerhub.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 聊天信息
 * @author 86182
 */
@Data
public class ChatMsg {

    //用户id
    private Long uId;;

    //token
    private String token;

    //用户名
    private String username;;

    //消息
    private String content;

    //时间
    private LocalDateTime date;

    //表情
    private String spirit;

    //是否初次连接
    private Boolean join;

    //头像图片
    private  String avatar;

}
