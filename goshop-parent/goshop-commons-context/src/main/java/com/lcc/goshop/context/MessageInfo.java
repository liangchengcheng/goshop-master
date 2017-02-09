package com.lcc.goshop.context;

import java.io.Serializable;

/**
 * Created by lcc on 2017/2/9.
 */
public class MessageInfo implements Serializable {

    private  String message;

    private String  returnUrl;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
}

