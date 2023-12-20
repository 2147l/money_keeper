package com.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "预算")
public class Budget {
    @TableId(type = IdType.AUTO)
    @Schema(description = "主键")
    private Integer id;
    @Schema(description = "用户id", example = "1")
    private Integer userId;
    @Schema(description = "年份月份", example = "2023-12")
    private String yearAndMonth;
    @Schema(description = "计划支出", example = "1000")
    private BigDecimal plan;
}
