package com.markerhub.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 接受忘记密码表单
 * @author 86182
 */
@Data
@ApiModel
public class ForgetPassDto {

    @ApiModelProperty("新密码")
    @Length(max = 20,min = 6)
    @NotBlank(message = "密码不能为空")
    private String newPassword;

    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空")
    @Length(max = 20)
    private String username;

    @ApiModelProperty("验证码")
    @Length(max = 4)
    @NotBlank(message = "验证码不能为空")
    private String code;
}
