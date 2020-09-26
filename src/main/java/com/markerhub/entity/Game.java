package com.markerhub.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 游戏类别
 * </p>
 *
 * @author hai
 * @since 2020-09-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("m_game")
public class Game implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 游戏编号
     */
    @TableId(value = "gid", type = IdType.AUTO)
    private Integer gid;

    /**
     * 游戏名
     */
    private String gName;


}
