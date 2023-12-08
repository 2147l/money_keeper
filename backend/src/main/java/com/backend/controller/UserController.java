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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/getById")
    @Operation(summary = "根据用户id查找用户")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "204", description = "查找的用户不存在", content = @Content),
            @ApiResponse(responseCode = "400", description = "缺少必要参数，或参数格式非法", content = @Content)
    })
    @Parameter(name = "userId", description = "用户id", example = "1")
    public ResponseEntity<List<User>> getById(@RequestParam int userId) {
        LambdaQueryWrapper<User> query = new LambdaQueryWrapper<>();
        query.eq(User::getId, userId);
        List<User> res = userService.list(query);
        if (res.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }

    @PostMapping("/login")
    @Operation(summary = "通过手机号和密码登录")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "登录成功", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "缺少必要参数，或参数格式非法", content = @Content),
            @ApiResponse(responseCode = "401", description = "账号或密码错误", content = @Content)
    })
    @Parameters(value = {
            @Parameter(name = "phone", description = "手机号码", required = true, example = "123"),
            @Parameter(name = "password", description = "密码", required = true, example = "123")
    })
    public ResponseEntity<User> login(@RequestParam String phone, @RequestParam String password) {
        LambdaQueryWrapper<User> query = new LambdaQueryWrapper<>();
        query.eq(User::getPhone, phone);
        query.eq(User::getPassword, password);
        List<User> res = userService.list(query);
        if (res.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(res.get(0), HttpStatus.OK);
        }

    }

    @PostMapping("/update")
    @Operation(summary = "更新用户信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "更新成功", content = @Content),
            @ApiResponse(responseCode = "400", description = "缺少必要参数，或参数格式非法", content = @Content)
    })
    public ResponseEntity update(@RequestBody User user) {
        userService.updateById(user);
        return ResponseEntity.ok().build();
    }

}
