package com.backend.mapper;

import com.backend.entity.Bill;
import com.backend.common.BillVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BillMapper extends BaseMapper<Bill> {

    // 连接查询，拼接账单的分类名以及图标
    @Select("select b.*, c.name, c.icon from bill b, category c where b.category_id = c.id and b.user_id = ${userId} order by b.date desc, b.id desc")
    public List<BillVo> listById(Integer userId);

    // 查询某年某月的所有账单
    @Select("select b.*, c.name, c.icon from bill b, category c where b.category_id = c.id and b.user_id = ${userId} and b.date like '${yearMonth}' order by b.date desc, b.id desc")
    public List<BillVo> listByYM(Integer userId, String yearMonth);
}
