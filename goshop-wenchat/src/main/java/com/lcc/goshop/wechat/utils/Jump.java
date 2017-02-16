package com.lcc.goshop.wechat.utils;

/**
 * Created by lcc on 2017/2/15.
 */
public class Jump {

    public static String get(String returnUrl,String message){
        StringBuffer sb = new StringBuffer("forward:/msg?message="+message+"&returnUrl="+returnUrl);
        return sb.toString();
    }
}
