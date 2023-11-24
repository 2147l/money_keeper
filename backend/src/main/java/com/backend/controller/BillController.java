package com.backend.controller;

import com.backend.entity.Bill;
import com.backend.mapper.BillMapper;
import com.backend.service.BillService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/bill")
public class BillController {
    @Autowired
    private BillService billService;

    @GetMapping("/test")
    public String test() {
        return "success";
    }

    @PostMapping("/create")
    public Boolean create(@RequestBody Bill bill) {
        // 前端没做完日期，后端自动添加当天
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        bill.setDate(simpleDateFormat.format(new Date()));
        return billService.save(bill);
    }

    @GetMapping("/list")
    public List<Bill> list(@RequestParam Integer userId) {
        QueryWrapper<Bill> qw = new QueryWrapper<>();
        qw.eq("user_id", userId);
        qw.orderByDesc("date");
        return billService.list(qw);
    }

}
