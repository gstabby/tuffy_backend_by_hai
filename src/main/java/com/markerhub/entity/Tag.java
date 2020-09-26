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
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNullApi;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 标签类
 * @author 86182
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("m_tag")
public class Tag implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "标签编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "标签名")
    @Length(max = 10)
    @NotBlank(message = "标签名不能为空")
    private String name;

    @ApiModelProperty(value = "标签描述")
    @Length(max = 50)
    @NotBlank(message = "标签描述不能为空")
    private String description;

    @ApiModelProperty(hidden = true)
    @TableField(exist = false)
    private Long blogNum;

}
