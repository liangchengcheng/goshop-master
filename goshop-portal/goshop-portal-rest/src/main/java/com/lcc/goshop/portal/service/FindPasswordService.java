package com.lcc.goshop.portal.service;

/**
 * Created by lcc on 2017/2/13.
 */
public interface FindPasswordService {
    String getContent(String username);

    int saveFindPassword(String username, String key);
}
