package com.lcc.goshop.manager.utils;

/**
 * Created by lcc on 2017/2/11.
 */
public class Jump {
    public static String get(String returnUrl,String message){
        returnUrl=returnUrl.replaceAll("&","%26");
        StringBuffer sb = new StringBuffer("forward:/msg?message="+message+"&returnUrl="+returnUrl);
        return sb.toString();
    }
}
