package com.lcc.goshop.manager.mapper;

import com.lcc.goshop.manager.pojo.Permission;

/**
 * Created by lcc on 2017/2/11.
 */
public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
}
