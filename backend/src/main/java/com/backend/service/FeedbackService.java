package com.backend.service;

import com.backend.entity.Feedback;
import com.backend.mapper.FeedbackMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FeedbackService extends ServiceImpl<FeedbackMapper, Feedback> {
    private final FeedbackMapper feedbackMapper;

    public FeedbackService(FeedbackMapper feedbackMapper) {
        this.feedbackMapper = feedbackMapper;
    }

    public int create(Integer userId, String content) {
        Feedback feedback = new Feedback();
        feedback.setUserId(userId);
        feedback.setContent(content);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        feedback.setCreateTime(dateFormat.format(new Date()));
        return feedbackMapper.insert(feedback);
    }
}
