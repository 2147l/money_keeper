package com.backend.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "收入和支出的封装类")
public class IncomeExpense {
    @Schema(description = "日期", example = "2023-12-13")
    private String date;
    @Schema(description = "收入", example = "2000.00")
    private BigDecimal income;
    @Schema(description = "支出", example = "-1000.00")
    private BigDecimal expense;
}
