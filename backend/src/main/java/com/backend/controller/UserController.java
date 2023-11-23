package com.backend.controller;

import com.backend.common.LoginParam;
import com.backend.entity.User;
import com.backend.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    // 测试接口
    @GetMapping("/test")
    public String test() {
        return "success";
    }

    // 登录成功返回id，否则返回空
    @PostMapping("/login")
    public Integer login(@RequestBody LoginParam loginParam) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("phone", loginParam.getPhone());
        qw.eq("password", loginParam.getPassword());
        List<User> res =  userService.list(qw);
        if (!res.isEmpty()) {
            return res.get(0).getId();
        } else {
            return null;
        }
    }
}
