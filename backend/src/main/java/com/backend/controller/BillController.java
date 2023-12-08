package com.backend.controller;

import com.backend.common.InAndOut;
import com.backend.entity.Bill;
import com.backend.mapper.BillMapper;
import com.backend.service.BillService;
import com.backend.common.BillVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/bill")
@Tag(name = "账单相关")
public class BillController {
    @Autowired
    private BillService billService;
    @Autowired
    private BillMapper billMapper;

    @Operation(summary = "创建新账单记录")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "创建成功", content = @Content),
            @ApiResponse(responseCode = "400", description = "缺少必要参数，或参数格式非法", content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<Boolean> createBill(@Parameter(description = "账单对象") @RequestBody Bill bill) {
        // 金额为0则不创建
        if (bill.getAmount().compareTo(BigDecimal.ZERO) == 0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        // 判断金额与收支类型是否统一
        if (bill.isType() ^ (bill.getAmount().compareTo(BigDecimal.ZERO) > 0))
            bill.setAmount(bill.getAmount().negate());
        bill.setId(null);
        // 如果没输入日期则默认为当天
        if (null == bill.getDate() || bill.getDate().isEmpty()) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            bill.setDate(simpleDateFormat.format(new Date()));
        }
        try {
            billService.save(bill);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "根据账单id删除记录")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功", content = @Content),
            @ApiResponse(responseCode = "204", description = "记录不存在", content = @Content),
            @ApiResponse(responseCode = "400", description = "缺少必要参数，或参数格式非法", content = @Content),
    })
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteBill(@Parameter(description = "账单id", example = "1") @RequestParam Integer billId) {
        Bill bill = billService.getById(billId);
        // 如果没有这条记录抛出204并返回
        if (null == bill)
            return ResponseEntity.noContent().build();
        billService.removeById(billId);
        return ResponseEntity.ok().body(true);
        // 未传参自动抛出400
    }

    @Operation(summary = "根据用户id查询所有记录")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = BillVo.class))),
            @ApiResponse(responseCode = "204", description = "无任何记录", content = @Content),
            @ApiResponse(responseCode = "400", description = "缺少必要参数，或参数格式非法", content = @Content)
    })
    @GetMapping("/listById")
    public ResponseEntity<List<BillVo>> listById(@Parameter(description = "用户id", example = "1") @RequestParam Integer userId) {
        List<BillVo> billVos = billMapper.listById(userId);
        if (billVos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(billVos, HttpStatus.OK);
        }
        // 未传参自动抛出错误400
    }

    @Operation(summary = "根据用户id查询某年某月的所有记录")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = BillVo.class))),
            @ApiResponse(responseCode = "204", description = "无任何记录", content = @Content),
            @ApiResponse(responseCode = "400", description = "缺少必要参数，或参数格式非法", content = @Content)
    })
    @Parameters(value = {
            @Parameter(name = "userId", description = "用户id", example = "1"),
            @Parameter(name = "year", description = "年份", example = "2023"),
            @Parameter(name = "month", description = "月份", example = "12"),
    })
    @GetMapping("/listByYM")
    public ResponseEntity<List<BillVo>> listByYM(@RequestParam Integer userId, @RequestParam String year, @RequestParam String month) {
        // 对传入的年份，月份进行校验
        if (year.length() != 4)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (month.length() != 2)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        String yearMonth = year + "-" + month + "%";
        // 调用mapper层手动定义的连表查询
        List<BillVo> billVos = billMapper.listByYM(userId, yearMonth);
        if (billVos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(billVos, HttpStatus.OK);
        }
        // 未传参自动抛出错误400
    }

    @Operation(summary = "获取某年的总收入，总支出")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = InAndOut.class))),
            @ApiResponse(responseCode = "400", description = "缺少必要参数，或参数格式非法", content = @Content)
    })
    @Parameters(value = {
            @Parameter(name = "userId", description = "用户id", example = "1"),
            @Parameter(name = "year", description = "年份", example = "2023"),
    })
    @GetMapping("/getYearSum")
    public ResponseEntity<InAndOut> getYearSum(@RequestParam Integer userId, @RequestParam String year) {
        // 对年份进行校验
        if (year.length() != 4)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        QueryWrapper<Bill> query = new QueryWrapper<>();
        // 对某年的收入进行求和
        query.select("sum(amount) as incomeSum");
        query.eq("user_id", userId);
        query.eq("type", true);
        query.likeRight("date", year);
        Bill bill1 = billService.getOne(query); // 通过sum语句查询，只需要第一条结果
        query.clear();  // 清空查询语句
        // 对某年的支出进行求和
        query.select("sum(amount) as expenseSum");
        query.eq("user_id", userId);
        query.eq("type", false);
        query.likeRight("date", year);
        Bill bill2 = billService.getOne(query);
        // 判断如果存在空查询，则该项结果需要手动置为0
        InAndOut result = new InAndOut();
        result.setIncome(bill1 == null ? BigDecimal.valueOf(0L) : bill1.getIncomeSum());
        result.setExpense(bill2 == null ? BigDecimal.valueOf(0L) : bill2.getExpenseSum());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

