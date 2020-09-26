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
 * 游戏分数
 * </p>
 *
 * @author hai
 * @since 2020-09-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("m_score")
public class Score implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分数编号
     */
    @TableId(value = "s_id", type = IdType.AUTO)
    private Integer sId;

    /**
     * 游戏编号
     */
    private Integer gId;

    /**
     * 分数
     */
    private Integer sScore;

    /**
     * 记录时间
     */
    private LocalDateTime sDate;

    /**
     * 用户编号
     */
    private Long uId;


}
