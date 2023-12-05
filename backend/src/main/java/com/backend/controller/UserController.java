package com.backend.controller;

import com.backend.entity.User;
import com.backend.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
@Tag(name = "用户相关", description = "")
public class UserController {
    @Autowired
    private UserService userService;

    // 使用id查询用户
    @GetMapping("/getById")
    @Operation(summary = "使用id查找用户")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功查询", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "查找的用户不存在", content = @Content)
    })
    @Parameter(name = "id", description = "用户id", example = "1")
    public ResponseEntity getById(int id) {
        LambdaQueryWrapper<User> query = new LambdaQueryWrapper<>();
        query.eq(User::getId, id);
        List<User> res = userService.list(query);
        if (res.isEmpty()) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(res);
        }
    }

    // 登录成功返回id，否则返回空
    @PostMapping("/login")
    @Operation(summary = "通过手机号和密码登录")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "登录成功", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "登录失败", content = @Content)
    })
    @Parameters(value = {
            @Parameter(name = "phone", description = "手机号码", required = true, example = "123"),
            @Parameter(name = "password", description = "密码", required = true, example = "123")
    })
    public ResponseEntity login(String phone, String password) {
        LambdaQueryWrapper<User> query = new LambdaQueryWrapper<>();
        query.eq(User::getPhone, phone);
        query.eq(User::getPassword, password);
        List<User> res = userService.list(query);
        if (res.isEmpty()) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(res.get(0));
        }
    }


}
