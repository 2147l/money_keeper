package com.backend.service;

import com.backend.entity.Feedback;
import com.backend.mapper.FeedbackMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService extends ServiceImpl<FeedbackMapper, Feedback> {
}
