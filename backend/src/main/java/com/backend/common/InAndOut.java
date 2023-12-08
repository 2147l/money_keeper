package com.backend.common;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "收入和支出的封装类")
public class InAndOut {
    @Schema(description = "收入", example = "2000.00")
    private BigDecimal income;
    @Schema(description = "支出", example = "-1000.00")
    private BigDecimal expense;
}
