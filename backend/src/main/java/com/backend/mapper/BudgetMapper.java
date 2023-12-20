package com.backend.mapper;

import com.backend.entity.Budget;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BudgetMapper extends BaseMapper<Budget> {
}
