package com.eumji.eutil.jxlsplus.model;

/**
 * 基础错误信息
 *
 * @email eumji025@gmail.com
 * @author:EumJi
 * @date: 17-12-23
 * @time: 下午7:44
 */
public class BaseError {

    private String errorInfo;

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    @Override
    public String toString() {
        return "BaseError{" +
                "errorInfo='" + errorInfo + '\'' +
                '}';
    }
}
