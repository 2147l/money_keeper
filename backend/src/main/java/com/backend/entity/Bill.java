package com.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "账单")
public class Bill {
    @TableId(type = IdType.AUTO)
    @Schema(description = "主键", example = "1")
    private Integer id;
    @Schema(description = "用户id", example = "1")
    private Integer userId;
    @Schema(description = "分类id", example = "1")
    private Integer categoryId;
    @Schema(description = "支出为false，收入为true", example = "false")
    private boolean type;
    @Schema(description = "金额", example = "-120.5")
    private BigDecimal amount;
    @Schema(description = "日期", example = "2021-3-27")
    private String date;
    @Schema(description = "备注", example = "聚会")
    private String note;
    @TableField(exist = false)
    private BigDecimal incomeSum;
    @TableField(exist = false)
    private BigDecimal expenseSum;
}
