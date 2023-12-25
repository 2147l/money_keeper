package com.backend.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "排行封装类")
public class Rank {
    @Schema(description = "金额", example = "1234.56")
    private BigDecimal money;
    @Schema(description = "占比(%)", example = "56.20")
    private BigDecimal percentage;
    @Schema(description = "类别名", example = "娱乐")
    private String name;
    @Schema(description = "类别图标", example = "src/assets/icon/娱乐.png")
    private String icon;
}
