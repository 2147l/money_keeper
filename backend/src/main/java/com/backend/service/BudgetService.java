package com.backend.service;

import com.backend.entity.Budget;
import com.backend.mapper.BudgetMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BudgetService extends ServiceImpl<BudgetMapper, Budget> {
}
