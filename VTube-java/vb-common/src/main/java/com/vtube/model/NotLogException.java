package com.vtube.model;

import com.vtube.enums.apiResultEnum;

import java.io.Serializable;

public class NotLogException extends Exception implements Serializable {
    private static final long serialVersionUID = 3843777456195536990L;

    /**
     * 异常编号
     */
    private Integer errorCode;

    /**
     * 异常信息
     */
    private String errorMsg;

    public NotLogException(String errorMsg) {
        this.errorMsg = errorMsg;
        this.errorCode = apiResultEnum.ERROR.getCode();
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}