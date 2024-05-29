package com.vtube.vo.Param;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginParam {
    @NotBlank(message = "用户名不能为空")
    private String userName;

    @NotBlank(message = "密码不能为空")
    private String password;
}
