package com.glacier.common.core.http;

import lombok.*;

import java.io.Serializable;

/**
 * HTTP结果封装
 *
 * @author glacier
 * @date 2019-10-14 15:53
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HttpResult<T> implements Serializable {

    private static final long serialVersionUID = -8984794300938868661L;
    private int code;
    private String msg;
    private T data;

    public static <T> HttpResult<T> error(String msg) {
        return error(500, msg);
    }

    public static <T> HttpResult<T> error(int code, String msg) {
        return error(code, msg, null);
    }

    public static <T> HttpResult<T> error(int code, String msg, T data) {
        return HttpResult.<T>builder()
                .code(code)
                .msg(msg)
                .data(data)
                .build();
    }

    public static <T> HttpResult<T> ok() {
        return ok(null, null);
    }

    public static <T> HttpResult<T> ok(T data) {
        return ok(null, data);
    }

    public static <T> HttpResult<T> ok(String msg, T data) {
        return HttpResult.<T>builder()
                .code(200)
                .msg(msg)
                .data(data)
                .build();
    }
}
