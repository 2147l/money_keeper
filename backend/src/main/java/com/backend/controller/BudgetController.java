package com.backend.controller;

import com.backend.common.BudgetVo;
import com.backend.entity.Budget;
import com.backend.entity.User;
import com.backend.service.BillService;
import com.backend.service.BudgetService;
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

import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
@CrossOrigin
@RequestMapping("/budget")
@Tag(name = "预算相关")
public class BudgetController {
    private final BudgetService budgetService;
    private final UserService userService;
    private final BillService billService;

    public BudgetController(BudgetService budgetService, UserService userService, BillService billService) {
        this.budgetService = budgetService;
        this.userService = userService;
        this.billService = billService;
    }

    @Operation(summary = "创建或更新预算")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "更改成功", content = @Content),
            @ApiResponse(responseCode = "204", description = "用户不存在", content = @Content),
            @ApiResponse(responseCode = "400", description = "缺少必要参数，或参数格式非法", content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<Budget> createBudget(@RequestBody Budget budget) {
        if (budget.getUserId() == null)
            return ResponseEntity.badRequest().build();
        User user = userService.getById(budget.getUserId());
        if (user == null)
            return ResponseEntity.noContent().build();
        if (budgetService.create(budget) > 0) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "查询本月预算使用情况")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = BudgetVo.class))),
            @ApiResponse(responseCode = "204", description = "用户不存在", content = @Content),
            @ApiResponse(responseCode = "400", description = "缺少必要参数，或参数格式非法", content = @Content)
    })
    @GetMapping("/get")
    public ResponseEntity<BudgetVo> get(@Parameter(description = "用户id", example = "1") @RequestParam Integer userId) {
        User user = userService.getById(userId);
        if (user == null)
            return ResponseEntity.noContent().build();
        Budget budget = budgetService.getThisMonth(userId);
        BudgetVo res = new BudgetVo();
        // 如果没设置预算，或预算为0就返回全0的结果
        if (budget == null || budget.getPlan().compareTo(BigDecimal.ZERO) == 0) {
            res.setPlan(BigDecimal.ZERO);
            res.setActual(BigDecimal.ZERO);
            res.setPercentage(BigDecimal.ZERO);
            return ResponseEntity.ok(res);
        }
        BigDecimal expense = billService.getThisMonthExpense(userId);
        res.setPlan(budget.getPlan());
        res.setActual(expense);
        res.setPercentage(expense.multiply(BigDecimal.valueOf(-100)).divide(budget.getPlan(), 2, RoundingMode.DOWN));
        return ResponseEntity.ok(res);
    }
}
