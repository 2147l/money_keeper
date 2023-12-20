package com.backend.controller;

import com.backend.common.BudgetVo;
import com.backend.entity.Bill;
import com.backend.entity.Budget;
import com.backend.entity.User;
import com.backend.service.BillService;
import com.backend.service.BudgetService;
import com.backend.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping("/budget")
@Tag(name = "预算相关")
public class BudgetController {
    @Autowired
    private BudgetService budgetService;
    @Autowired
    private UserService userService;
    @Autowired
    private BillService billService;

    @Operation(summary = "创建预算")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "创建成功", content = @Content),
            @ApiResponse(responseCode = "204", description = "用户不存在", content = @Content),
            @ApiResponse(responseCode = "400", description = "缺少必要参数，或参数格式非法", content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity createBudget(@Parameter(description = "预算") @RequestBody Budget budget) {
        User user = userService.getById(budget.getUserId());
        if (user == null)
            return ResponseEntity.noContent().build();
        if (null == budget.getYearAndMonth() || budget.getYearAndMonth().isEmpty()) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
            budget.setYearAndMonth(simpleDateFormat.format(new Date()));
        } else {
            // 格式化日期
            if (budget.getYearAndMonth().length() < 7) {
                String tmp = budget.getYearAndMonth().substring(0, 5) + '0' + budget.getYearAndMonth().substring(5);
                budget.setYearAndMonth(tmp);
            }
        }
        // 查询该用户该月是否已经有预算了
        LambdaQueryWrapper<Budget> query = new LambdaQueryWrapper<>();
        query.eq(Budget::getUserId, budget.getUserId());
        query.eq(Budget::getYearAndMonth, budget.getYearAndMonth());
        Budget tmp = budgetService.getOne(query);
        if (tmp == null) {
            // 没有则插入
            budgetService.save(budget);
        } else {
            // 有则更新
            budget.setId(tmp.getId());
            budgetService.updateById(budget);
        }
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "查询本月预算信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功", content = @Content(
                    mediaType = "application/json",schema = @Schema(implementation = BudgetVo.class))),
            @ApiResponse(responseCode = "204", description = "用户不存在", content = @Content),
            @ApiResponse(responseCode = "400", description = "缺少必要参数，或参数格式非法", content = @Content)
    })
    @GetMapping("/get")
    public ResponseEntity createBudget(@Parameter(description = "用户id", example = "1") @RequestParam Integer userId) {
        User user = userService.getById(userId);
        if (user == null)
            return ResponseEntity.noContent().build();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        String tmp = simpleDateFormat.format(new Date());
        // 查询是否设置了预算
        LambdaQueryWrapper<Budget> query = new LambdaQueryWrapper<>();
        query.eq(Budget::getUserId, userId);
        query.eq(Budget::getYearAndMonth, tmp);
        Budget budget =  budgetService.getOne(query);
        BudgetVo res = new BudgetVo();
        // 如果没设置预算,或预算为0就返回全0的结果
        if (budget == null || budget.getPlan().compareTo(BigDecimal.ZERO) == 0) {
            res.setPlan(BigDecimal.ZERO);
            res.setActual(BigDecimal.ZERO);
            res.setPercentage(0);
            return ResponseEntity.ok(res);
        }
        res.setPlan(budget.getPlan());
        // 查询本月的支出
        QueryWrapper<Bill> query2 = new QueryWrapper<>();
        query2.select("sum(amount) as expenseSum");
        query2.eq("user_id", userId);
        query2.eq("type", false);
        query2.likeRight("date", tmp);
        Bill bill =  billService.getOne(query2);
        // 判断为空则手动更改为0
        res.setActual(bill == null ? BigDecimal.valueOf(0L) : bill.getExpenseSum());
        res.setPercentage(res.getActual().intValue() * (-100) / res.getPlan().intValue());
        return ResponseEntity.ok(res);
    }
}
