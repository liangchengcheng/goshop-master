package com.lcc.goshop.manager.mapper;

import com.lcc.goshop.manager.pojo.FindPassword;

/**
 * Created by lcc on 2017/2/11.
 */
public interface FindPasswordMapper {
    int insert(FindPassword record);

    int insertSelective(FindPassword record);

    /**
     * 删除过期
     */
    void deleteInvalid();

    /**
     * 通过编码查找用户名
     */
    String findByKey(String key);

    /**
     * 删除此链接
     */
    int delete(String key);
}
