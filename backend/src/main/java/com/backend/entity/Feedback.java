package com.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "反馈")
public class Feedback {
    @TableId(type = IdType.AUTO)
    @Schema(description = "主键", example = "1")
    private Integer id;

    @Schema(description = "用户id", example = "1")
    private Integer userId;

    @Schema(description = "创建时间", example = "2023-12-07 09:00:00")
    private String createTime;

    @Schema(description = "反馈内容", example = "希望能自定义主题颜色")
    private String content;
}
