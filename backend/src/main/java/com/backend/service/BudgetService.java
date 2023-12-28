package com.backend.service;

import com.backend.entity.Budget;
import com.backend.mapper.BudgetMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class BudgetService extends ServiceImpl<BudgetMapper, Budget> {
    private final BudgetMapper budgetMapper;

    public BudgetService(BudgetMapper budgetMapper) {
        this.budgetMapper = budgetMapper;
    }

    public int create(Budget budget) {
        if (null == budget.getYearAndMonth() || budget.getYearAndMonth().isEmpty()) {
            // 没传日期就将日期设为今年本月
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
            budget.setYearAndMonth(simpleDateFormat.format(new Date()));
        } else {
            // 格式化日期
            if (budget.getYearAndMonth().length() < 7) {
                String tmp = budget.getYearAndMonth().substring(0, 5) + '0' + budget.getYearAndMonth().substring(5);
                budget.setYearAndMonth(tmp);
            }
        }
        LambdaQueryWrapper<Budget> query = new LambdaQueryWrapper<>();
        query.eq(Budget::getUserId, budget.getUserId());
        query.eq(Budget::getYearAndMonth, budget.getYearAndMonth());
        Budget tmp = budgetMapper.selectOne(query);
        if (tmp == null) {
            return budgetMapper.insert(budget);
        } else {
            budget.setId(tmp.getId());
            return budgetMapper.updateById(budget);
        }
    }

    public Budget getThisMonth(Integer userId) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        String tmp = simpleDateFormat.format(new Date());
        // 查询是否设置了预算
        LambdaQueryWrapper<Budget> query = new LambdaQueryWrapper<>();
        query.eq(Budget::getUserId, userId);
        query.eq(Budget::getYearAndMonth, tmp);
        return budgetMapper.selectOne(query);
    }
}
