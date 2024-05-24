package com.vtube.model;

import com.vtube.constant.charConstant;
import com.vtube.enums.apiResultEnum;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ApiResult<T> implements Serializable {
    private static final long serialVersionUID = -4073679724104914374L;

    private Integer code;
    private T data;
    private String message;
    private LocalDateTime time;

    public ApiResult(Integer code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.time = LocalDateTime.now();
    }


    /**
     * 根据布尔值判断成功通知还是失败通知
     *
     * @param b 布尔值
     * @return 结果集
     */
    public static ApiResult<String> flag(Boolean b) {
        if (b) {
            return ok();
        } else {
            return error();
        }
    }
    /**
     * 成功通知结果
     *
     * @param data 数据
     * @return 成功结果
     */
    public static <T> ApiResult<T> ok(T data) {
        return new ApiResult<>(apiResultEnum.SUCCESS.getCode(), data, apiResultEnum.SUCCESS.getDesc());
    }

    /**
     * 成功通知结果（无数据返回）
     *
     * @return 成功结果
     */
    public static ApiResult<String> ok() {
        return new ApiResult<>(apiResultEnum.SUCCESS.getCode(), charConstant.EMPTY_STRING, apiResultEnum.SUCCESS.getDesc());
    }

    /**
     * 失败通知结果（无数据返回）
     *
     * @return 错误结果
     */
    public static ApiResult<String> error() {
        return new ApiResult<>(apiResultEnum.ERROR.getCode(), charConstant.EMPTY_STRING, apiResultEnum.ERROR.getDesc());
    }

    /**
     * 失败通知结果
     *
     * @param data 数据
     * @return 成功结果
     */
    public static <T> ApiResult<T> error(T data) {
        return error(data, apiResultEnum.ERROR.getCode());
    }

    /**
     * 失败通知结果
     *
     * @param data    通知数据
     * @param message 通知消息
     * @return 成功结果
     */
    public static <T> ApiResult<T> error(T data, String message) {
        return new ApiResult<>(apiResultEnum.ERROR.getCode(), data, message);
    }

    /**
     * 失败通知结果
     *
     * @param message 通知消息
     * @return 成功结果
     */
    public static ApiResult<String> error(String message) {
        return error(charConstant.EMPTY_STRING, message);
    }

    /**
     * 失败通知结果
     *
     * @param errorCode 错误码
     * @param message 通知消息
     * @return 成功结果
     */
    public static ApiResult<String> error(Integer errorCode, String message) {
        return new ApiResult<>(errorCode, charConstant.EMPTY_STRING, message);
    }

    /**
     * 失败通知结果
     *
     * @param data 数据
     * @return 成功结果
     */
    public static <T> ApiResult<T> error(T data, Integer errorCode) {
        return new ApiResult<>(errorCode, data, apiResultEnum.SUCCESS.getDesc());
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public ApiResult<T> setTime(LocalDateTime time) {
        this.time = time;
        return this;
    }
}