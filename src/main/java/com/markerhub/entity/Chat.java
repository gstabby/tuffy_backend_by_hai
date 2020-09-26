package com.markerhub.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 聊天信息
 * </p>
 *
 * @author hai
 * @since 2020-09-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("m_chat")
public class Chat implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 聊天信息唯一标识
     */
    @TableId(value = "c_id", type = IdType.AUTO)
    private Integer cId;

    /**
     * 用户编号
     */
    private Long uId;

    /**
     * 聊天内容
     */
    private String cContent;

    /**
     * 聊天时间
     */
    private LocalDateTime cDate;


}
