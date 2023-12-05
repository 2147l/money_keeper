package com.backend.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "登录参数")
public class LoginParam {
    @Schema(description = "电话号码", example = "123")
    private String phone;

    @Schema(description = "密码", example = "123")
    private String password;
}
