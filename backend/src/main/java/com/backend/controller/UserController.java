package com.backend.controller;

import com.backend.entity.User;
import com.backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
@Tag(name = "用户相关")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getById")
    @Operation(summary = "根据用户id查询用户信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "204", description = "用户id不存在", content = @Content),
            @ApiResponse(responseCode = "400", description = "缺少必要参数，或参数格式非法", content = @Content)
    })
    @Parameter(name = "userId", description = "用户id", required = true, example = "1")
    public ResponseEntity<User> getById(@RequestParam Integer userId) {
        User res =  userService.getById(userId);
        if (res == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(res);
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
        User res = userService.login(phone, password);
        if (res == null) {
            return ResponseEntity.status(401).build();
        } else {
            return ResponseEntity.ok(res);
        }
    }

    @PostMapping("/update")
    @Operation(summary = "根据id更新用户信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "更新成功", content = @Content),
            @ApiResponse(responseCode = "204", description = "用户不存在", content = @Content),
            @ApiResponse(responseCode = "400", description = "缺少必要参数，或参数格式非法", content = @Content)
    })
    public ResponseEntity<User> update(@RequestBody User user) {
        User tmp = userService.getById(user.getId());
        if (tmp == null)
            return ResponseEntity.noContent().build();
        userService.updateById(user);
        return ResponseEntity.ok().build();
    }

}

