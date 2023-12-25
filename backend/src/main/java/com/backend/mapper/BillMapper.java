package com.backend.mapper;

import com.backend.common.Rank;
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
    List<BillVo> listById(Integer userId);

    // 查询某年某月的所有账单
    @Select("select b.*, c.name, c.icon from bill b, category c where b.category_id = c.id and b.user_id = ${userId} and b.date like '${yearMonth}' order by b.date desc, b.id desc")
    List<BillVo> listByYM(Integer userId, String yearMonth);

    // 查询某年某月的收支排行
    @Select("select sum(bill.amount) as 'money', category.name, category.icon from bill, category " +
            "where bill.category_id = category.id and user_id = ${userId} and date like '${yearMonth}%' and type = ${type} group by category_id order by money;")
    List<Rank> rankMonth(Integer userId, String yearMonth, boolean type);
}
