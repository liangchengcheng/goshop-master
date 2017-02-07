package com.lcc.goshop.commons.pojo;

/**
 * Created by lcc on 2017/2/7.
 *
 * 错误信息返回类。
 */
public class ErrorMessage {

    private int code;

    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
