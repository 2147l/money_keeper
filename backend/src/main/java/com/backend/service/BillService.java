package com.backend.service;

import com.backend.entity.Bill;
import com.backend.mapper.BillMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService extends ServiceImpl<BillMapper, Bill> {

}
