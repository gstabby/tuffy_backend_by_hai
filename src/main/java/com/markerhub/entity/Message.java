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
 * 玩家留言表
 * </p>
 *
 * @author hai
 * @since 2020-09-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("m_message")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 留言唯一标识符
     */
    @TableId(value = "m_id", type = IdType.AUTO)
    private Integer mId;

    /**
     * 用户编号
     */
    private Long uId;

    /**
     * 留言内容
     */
    private String mContent;

    /**
     * 留言时间
     */
    private LocalDateTime mDate;


}
