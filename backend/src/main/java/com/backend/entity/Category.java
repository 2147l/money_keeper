package com.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "分类")
public class Category {
    @TableId(type = IdType.AUTO)
    @Schema(description = "主键", example = "1")
    private Integer id;
    @Schema(description = "名称", example = "餐饮")
    private String name;
    @Schema(description = "图标", example = "src/assets/icon/餐饮.png")
    private String icon;
}
