package com.backend.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "封装返回对象")
public class Result<T> {
    @Schema(description = "状态码")
    private int code;
    @Schema(description = "状态信息")
    private String message;
    @Schema(description = "返回数据")
    private T data;

    // 有返回值构造方法
    public static <T> Result<T> success(int code, String message, T data) {
        Result<T> result = new Result<>();
        result.code = code;
        result.message = message;
        result.data = data;
        return result;
    }

    // 无返回值构造方法
    public static <T> Result<T> fail(int code, String message) {
        Result<T> result = new Result<>();
        result.code = code;
        result.message = message;
        return result;
    }
}
