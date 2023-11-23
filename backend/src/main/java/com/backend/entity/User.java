package com.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String phone;
    private String password;
    private String username;
    private String avatar;
    private String sex;
    private String wechat;
    private String email;
}
