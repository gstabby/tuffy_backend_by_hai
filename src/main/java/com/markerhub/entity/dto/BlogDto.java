package com.markerhub.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.markerhub.entity.Category;
import com.markerhub.entity.Tag;
import com.markerhub.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author 86182
 */
@ApiModel
@Data
public class BlogDto {

    @ApiModelProperty(value = "博客编号")
    private  Long id;

    @ApiModelProperty(value = "博客标题")
    @Length(max = 100,message = "最大长度100")
    @NotBlank(message = "标题不能为空")
    private String title;

    @ApiModelProperty(value = "博客摘要")
    @Length(max = 100,message = "最大长度100")
    @NotBlank(message = "摘要不能为空")
    private String description;

    @ApiModelProperty(value = "博客内容")
    @NotBlank(message = "内容不能为空")
    private String content;


    @ApiModelProperty(value = "封面图片")
    @NotNull(message = "文件不能为空")
    private MultipartFile file;

    @ApiModelProperty(value = "文章类型")
    @NotNull(message = "文章类型不能为空")
    private Long category;

    @ApiModelProperty(value = "文章标签")
    //@NotNull(message = "文章标签不能为空")
    @Size(min = 1, message = "标签不能为空")
    private Long[] tags;
}
