package com.markerhub.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 分类
 * @author 86182
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("m_category")
@ApiModel
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分类编号")
    @TableId(value = "id", type = IdType.AUTO)
    Long id;

    @ApiModelProperty(value = "分类名")
    @Size(max = 10)
    @NotBlank(message = "分类名不能为空")
    String title;

    @ApiModelProperty(value = "分类描述")
    @Size(max = 50)
    @NotBlank(message = "分类描述不能为空")
    String description;

    @ApiModelProperty(value = "分类下文章数")
    @TableField(exist = false)
    Long blogNum;

}
