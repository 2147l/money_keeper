package com.backend.controller;

import com.backend.entity.Bill;
import com.backend.mapper.BillMapper;
import com.backend.service.BillService;
import com.backend.vo.BillVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
            @ApiResponse(responseCode = "400", description = "传入的账单属性非法", content = @Content)
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

    @Operation(summary = "根据用户id返回所有记录")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = BillVo.class))),
            @ApiResponse(responseCode = "400", description = "缺少用户id", content = @Content)
    })
    @GetMapping("/listById")
    public ResponseEntity<List<BillVo>> listById(@Parameter(description = "用户id", example = "1") @RequestParam Integer userId) {
        return new ResponseEntity<>(billMapper.listById(userId), HttpStatus.OK);
        // 未传参自动抛出错误400
    }

    @Operation(summary = "根据用户id查询某年某月的所有记录")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = BillVo.class))),
            @ApiResponse(responseCode = "400", description = "传参格式非法", content = @Content)
    })
    @Parameters(value = {
            @Parameter(name = "userId", description = "用户id", example = "1"),
            @Parameter(name = "year", description = "年份", example = "2023"),
            @Parameter(name = "month", description = "月份", example = "12"),
    })
    @GetMapping("/listByYM")
    public ResponseEntity<List<BillVo>> listByYM(@RequestParam Integer userId, @RequestParam String year, @RequestParam String month) {
        if (year.length() != 4)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (month.length() != 2)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        String yearMonth = year + "-" + month + "%";
        return new ResponseEntity<>(billMapper.listByYM(userId, yearMonth), HttpStatus.OK);
        // 未传参自动抛出错误400
    }


}

