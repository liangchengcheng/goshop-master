package com.lcc.goshop.shiro.service;

/**
 * Created by lcc on 2017/2/11.
 */
public interface PasswordService {
    String encryptPassword(String password, String salt);
}
