package com.backend.controller;

import com.backend.common.BillVo;
import com.backend.common.IncomeExpense;
import com.backend.common.Rank;
import com.backend.entity.Bill;
import com.backend.service.BillService;
import com.backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/bill")
@Tag(name = "账单相关")
public class BillController {
    private final BillService billService;
    private final UserService userService;

    public BillController(BillService billService, UserService userService) {
        this.billService = billService;
        this.userService = userService;
    }

    @Operation(summary = "创建新账单记录")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "创建成功", content = @Content),
            @ApiResponse(responseCode = "204", description = "用户不存在", content = @Content),
            @ApiResponse(responseCode = "400", description = "缺少必要参数，或参数格式非法", content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<Bill> create(@Parameter(description = "账单对象") @RequestBody Bill bill) {
        if (bill == null)
            return ResponseEntity.badRequest().build();
        if (bill.getUserId() == null)
            return ResponseEntity.badRequest().build();
        if (userService.getById(bill.getUserId()) == null)
            return ResponseEntity.noContent().build();
        if (billService.create(bill) > 0) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "根据账单id修改记录")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "创建成功", content = @Content),
            @ApiResponse(responseCode = "204", description = "记录不存在", content = @Content),
            @ApiResponse(responseCode = "400", description = "缺少必要参数，或参数格式非法", content = @Content)
    })
    @PostMapping("/update")
    public ResponseEntity<Bill> update(@Parameter(description = "账单对象") @RequestBody Bill bill) {
        if (bill == null || bill.getId() == null)
            return ResponseEntity.badRequest().build();
        if (billService.getById(bill.getId()) == null)
            return ResponseEntity.noContent().build();
        billService.updateById(bill);
        return ResponseEntity.ok().build();
    }

        @Operation(summary = "根据账单id删除记录")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功", content = @Content),
            @ApiResponse(responseCode = "204", description = "记录不存在", content = @Content),
            @ApiResponse(responseCode = "400", description = "缺少必要参数，或参数格式非法", content = @Content),
    })
    @DeleteMapping("/delete")
    public ResponseEntity<Bill> deleteBill(@Parameter(description = "账单id", required = true, example = "1") @RequestParam Integer billId) {
        if (billId == null)
            return ResponseEntity.badRequest().build();
        Bill bill = billService.getById(billId);
        // 如果没有这条记录抛出204并返回
        if (null == bill)
            return ResponseEntity.noContent().build();
        billService.removeById(billId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "根据用户id查询所有记录")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = BillVo.class))),
            @ApiResponse(responseCode = "204", description = "用户不存在", content = @Content),
            @ApiResponse(responseCode = "400", description = "缺少必要参数，或参数格式非法", content = @Content)
    })
    @GetMapping("/listById")
    public ResponseEntity<List<BillVo>> listById(@Parameter(description = "用户id", required = true, example = "1") @RequestParam Integer userId) {
        if (userId == null)
            return ResponseEntity.badRequest().build();
        if (userService.getById(userId) == null)
            return ResponseEntity.noContent().build();
        List<BillVo> billVos = billService.getList(userId);
        return ResponseEntity.ok(billVos);
    }

    @Operation(summary = "根据用户id查询某年某月的所有记录")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = BillVo.class))),
            @ApiResponse(responseCode = "204", description = "用户不存在", content = @Content),
            @ApiResponse(responseCode = "400", description = "缺少必要参数，或参数格式非法", content = @Content)
    })
    @Parameters(value = {
            @Parameter(name = "userId", description = "用户id", required = true, example = "1"),
            @Parameter(name = "year", description = "年份", required = true, example = "2023"),
            @Parameter(name = "month", description = "月份", required = true, example = "12"),
    })
    @GetMapping("/listByYM")
    public ResponseEntity<List<BillVo>> listByYM(@RequestParam Integer userId, @RequestParam String year, @RequestParam String month) {
        if (userId == null || year == null || month == null)
            return ResponseEntity.badRequest().build();
        if (userService.getById(userId) == null)
            return ResponseEntity.noContent().build();
        List<BillVo> res = billService.listByYM(userId, year, month);
        return ResponseEntity.ok(res);
    }

    @Operation(summary = "根据用户id查询某年的总收入，总支出")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = IncomeExpense.class))),
            @ApiResponse(responseCode = "204", description = "用户不存在", content = @Content),
            @ApiResponse(responseCode = "400", description = "缺少必要参数，或参数格式非法", content = @Content)
    })
    @Parameters(value = {
            @Parameter(name = "userId", description = "用户id", required = true, example = "1"),
            @Parameter(name = "year", description = "年份", required = true, example = "2023"),
    })
    @GetMapping("/getYearSum")
    public ResponseEntity<IncomeExpense> getYearSum(@RequestParam Integer userId, @RequestParam String year) {
        if (userId == null || year == null)
            return ResponseEntity.badRequest().build();
        if (userService.getById(userId) == null)
            return ResponseEntity.noContent().build();
        IncomeExpense res = billService.getYearSum(userId, year);
        return ResponseEntity.ok(res);
    }

    @Operation(summary = "根据用户id查询某年每个月的总收入，总支出")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = IncomeExpense.class))),
            @ApiResponse(responseCode = "204", description = "用户不存在", content = @Content),
            @ApiResponse(responseCode = "400", description = "缺少必要参数，或参数格式非法", content = @Content)
    })
    @Parameters(value = {
            @Parameter(name = "userId", description = "用户id", required = true, example = "1"),
            @Parameter(name = "year", description = "年份", required = true, example = "2023"),
    })
    @GetMapping("/getYearMonthSum")
    public ResponseEntity<List<IncomeExpense>> getYearMonthSum(@RequestParam Integer userId, @RequestParam String year) {
        if (userId == null || year == null)
            return ResponseEntity.badRequest().build();
        if (userService.getById(userId) == null)
            return ResponseEntity.noContent().build();
        List<IncomeExpense> res = billService.getYearMonthSum(userId, year);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "根据用户id获取本周（上周，上上周）的每日收支")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = IncomeExpense.class))),
            @ApiResponse(responseCode = "204", description = "用户不存在", content = @Content),
            @ApiResponse(responseCode = "400", description = "缺少必要参数，或参数格式非法", content = @Content)
    })
    @Parameters(value = {
            @Parameter(name = "userId", description = "用户id", required = true, example = "1"),
            @Parameter(name = "flag", description = "0表示本周，1表示上周，2表示上上周", required = true, example = "0")
    })
    @GetMapping("/getThisWeek")
    public ResponseEntity<List<IncomeExpense>> getThisWeek(@RequestParam Integer userId, @RequestParam Integer flag) {
        if (userId == null || flag == null)
            return ResponseEntity.badRequest().build();
        if (flag != 0 && flag != 1 && flag != 2) {
            return ResponseEntity.badRequest().build();
        }
        if (userService.getById(userId) == null)
            return ResponseEntity.noContent().build();
        List<IncomeExpense> res = billService.getThisWeek(userId, flag);
        return ResponseEntity.ok(res);
    }

    @Operation(summary = "根据用户id查询本月（上月，上上月）的每日收支")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = IncomeExpense.class))),
            @ApiResponse(responseCode = "204", description = "用户不存在", content = @Content),
            @ApiResponse(responseCode = "400", description = "缺少必要参数，或参数格式非法", content = @Content)
    })
    @Parameters(value = {
            @Parameter(name = "userId", description = "用户id", required = true, example = "1"),
            @Parameter(name = "flag", description = "0表示本月，1表示上月，2表示上上月", required = true, example = "0")
    })
    @GetMapping("/getThisMonth")
    public ResponseEntity<List<IncomeExpense>> getThisMonth(@RequestParam Integer userId, @RequestParam Integer flag) {
        if (userId == null || flag == null)
            return ResponseEntity.badRequest().build();
        if (flag != 0 && flag != 1 && flag != 2) {
            return ResponseEntity.badRequest().build();
        }
        if (userService.getById(userId) == null)
            return ResponseEntity.noContent().build();
        List<IncomeExpense> res = billService.getThisMonth(userId, flag);
        return ResponseEntity.ok(res);
    }

    @Operation(summary = "根据用户id查询今年（去年，前年）的每月收支")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = IncomeExpense.class))),
            @ApiResponse(responseCode = "204", description = "用户不存在", content = @Content),
            @ApiResponse(responseCode = "400", description = "缺少必要参数，或参数格式非法", content = @Content)
    })
    @Parameters(value = {
            @Parameter(name = "userId", description = "用户id", required = true, example = "1"),
            @Parameter(name = "flag", description = "0表示今年，1表示去年，2表示前年", required = true, example = "0")
    })
    @GetMapping("/getThisYear")
    public ResponseEntity<List<IncomeExpense>> getThisYear(@RequestParam Integer userId, @RequestParam Integer flag) {
        if (userId == null || flag == null)
            return ResponseEntity.badRequest().build();
        if (flag != 0 && flag != 1 && flag != 2) {
            return ResponseEntity.badRequest().build();
        }
        if (userService.getById(userId) == null)
            return ResponseEntity.noContent().build();
        List<IncomeExpense> res = billService.getThisYear(userId, flag);
        return ResponseEntity.ok(res);
    }

    @Operation(summary = "根据用户id查询某年某月的消费排行")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Rank.class))),
            @ApiResponse(responseCode = "204", description = "用户不存在", content = @Content),
            @ApiResponse(responseCode = "400", description = "缺少必要参数，或参数格式非法", content = @Content)
    })
    @Parameters(value = {
            @Parameter(name = "userId", description = "用户id", required = true, example = "1"),
            @Parameter(name = "year", description = "年份", required = true, example = "2023"),
            @Parameter(name = "month", description = "月份", required = true, example = "12"),
            @Parameter(name = "flag", description = "false为支出，true为收入", required = true, example = "false")
    })
    @GetMapping("/getRank")
    public ResponseEntity<List<Rank>> getRank(@RequestParam Integer userId, @RequestParam String year, @RequestParam String month, @RequestParam boolean flag) {
        if (userId == null || year == null || month == null)
            return ResponseEntity.badRequest().build();
        if (userService.getById(userId) == null)
            return ResponseEntity.noContent().build();
        List<Rank> res = billService.getRank(userId, year, month, flag);
        return ResponseEntity.ok(res);
    }

}