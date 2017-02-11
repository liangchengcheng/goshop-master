package com.lcc.goshop.manager.mapper;

import com.lcc.goshop.manager.pojo.Role;

/**
 * Created by lcc on 2017/2/11.
 */
public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    Role findByName(String name);

    Role findOfUserOne(Long id);
}
