package com.lcc.goshop.portal.service;

/**
 * Created by lcc on 2017/2/13.
 */
public interface EMailService {
    /**
     * 发送邮件
     * @param email 邮件地址
     * @param title 邮件标题
     * @param emailContent 邮件内容
     */
    void send(String email,String title, String emailContent);
}