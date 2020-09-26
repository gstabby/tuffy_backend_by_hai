package com.markerhub.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * 用于接受评论
 * @author 86182
 */
@Data
@ApiModel
public class CommentDto {

    @ApiModelProperty(value = "博客编号")
    @NotNull
    private Long bId;

    @ApiModelProperty(value = "被回复评论编号")
    private Integer cCid;


    @ApiModelProperty(value = "被回复者")
    private Integer targeId;

    @ApiModelProperty(value = "评论内容")
    @Length(max = 500,message = "最大长度100")
    @NotBlank(message = "评论内容不能为空")
    private String content;
}
