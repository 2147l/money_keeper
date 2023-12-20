package com.backend.common;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "封装预算相关的信息")
public class BudgetVo {
    @Schema(description = "预算", example = "1000")
    private BigDecimal plan;
    @Schema(description = "实际支出", example = "900")
    private BigDecimal actual;
    @Schema(description = "支出比例(%)", example = "90")
    private int percentage;
}
