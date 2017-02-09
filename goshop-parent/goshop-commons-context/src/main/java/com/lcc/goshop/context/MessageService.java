package com.lcc.goshop.context;

/**
 * Created by lcc on 2017/2/9.
 */
public interface MessageService {

    void set(String key,MessageInfo value);

    MessageInfo get(String key);
}
