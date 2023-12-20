package com.backend.controller;

import com.backend.entity.Feedback;
import com.backend.entity.User;
import com.backend.service.FeedbackService;
import com.backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/feedback")
@Tag(name = "反馈相关")
public class FeedBackController {
    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private UserService userService;

    @Operation(summary = "创建一条反馈")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "创建成功", content = @Content),
            @ApiResponse(responseCode = "204", description = "用户不存在", content = @Content),
            @ApiResponse(responseCode = "400", description = "缺少必要参数，或参数格式非法", content = @Content)
    })
    @Parameters( value = {
            @Parameter(name = "userId", description = "用户id", example = "1"),
            @Parameter(name = "content", description = "反馈内容", example = "希望能自定义主题颜色")
    })
    @PostMapping("/create")
    public ResponseEntity createFeedback(@RequestParam Integer userId, @RequestParam String content) {
        User user = userService.getById(userId);
        if (user == null)
            return ResponseEntity.noContent().build();
        if (content == null || content.isEmpty())
            return ResponseEntity.badRequest().build();
        Feedback feedback = new Feedback();
        feedback.setUserId(userId);
        feedback.setContent(content);
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        feedback.setCreateTime(dateFormat.format(new Date()));
        feedbackService.save(feedback);
        return ResponseEntity.ok().build();
    }
}
