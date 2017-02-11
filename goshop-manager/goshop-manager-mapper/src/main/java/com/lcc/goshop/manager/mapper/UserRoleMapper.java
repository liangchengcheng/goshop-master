package com.lcc.goshop.manager.mapper;

import com.lcc.goshop.manager.pojo.UserRole;
import org.apache.ibatis.annotations.Param;

/**
 * Created by lcc on 2017/2/11.
 */
public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole findByUIdAndRId(@Param("uId")Long userId, @Param("rId") Long roleId);

    void deleteByUIdAndRId(@Param("uId")Long userId , @Param("rId")Long roleId);
}
