package com.backend.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "拼接分类后的账单记录")
public class BillVo {
    @Schema(description = "主键", example = "1")
    private Integer id;
    @Schema(description = "用户id", example = "1")
    private Integer userId;
    @Schema(description = "分类id", example = "1")
    private Integer categoryId;
    @Schema(description = "支出为false，收入为true", example = "false")
    private boolean type;
    @Schema(description = "金额", example = "120.5")
    private BigDecimal amount;
    @Schema(description = "日期", example = "2021-3-27")
    private String date;
    @Schema(description = "备注", example = "聚会")
    private String note;
    @Schema(description = "名称", example = "餐饮")
    private String name;
    @Schema(description = "图标", example = "src/assets/icon/餐饮.png")
    private String icon;
}
