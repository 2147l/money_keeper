package com.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "用户")
public class User {
    @TableId(type = IdType.AUTO)
    @Schema(description = "主键", example = "1")
    private Integer id;
    @Schema(description = "电话号码", example = "13512345678")
    private String phone;
    @JsonIgnore
    @Schema(description = "密码", example = "123456")
    private String password;
    @Schema(description = "昵称", example = "小王")
    private String username;
    @Schema(description = "头像", example = "src/assets/avtar/1.png")
    private String avatar;
    @Schema(description = "性别", example = "男")
    private String sex;
    @Schema(description = "微信号", example = "xiaowang666")
    private String wechat;
    @Schema(description = "邮箱", example = "xiaowang@email.com")
    private String email;
}
