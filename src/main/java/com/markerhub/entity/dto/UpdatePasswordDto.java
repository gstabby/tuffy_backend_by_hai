package com.markerhub.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 接受修改密码表单
 * @author 86182
 */
@Data
@ApiModel
public class UpdatePasswordDto {
    @ApiModelProperty("新密码")
    @Length(max = 20,min = 6)
    @NotBlank(message = "密码不能为空")
    private String oldPassword;

    @ApiModelProperty("新密码")
    @Length(max = 20,min = 6)
    @NotBlank(message = "密码不能为空")
    private String newPassword;
}
