package com.markerhub.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 接收修改用户信息表单
 */
@Data
@ApiModel
public class UpdateUserDto {
    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空")
    @Length(max = 20)
    private String username;

    @ApiModelProperty("邮箱")
    @Email
    @NotBlank(message = "邮箱不能为空")
    private String email;

    @ApiModelProperty("验证码")
    @Length(max = 4)
    private String code;
}
