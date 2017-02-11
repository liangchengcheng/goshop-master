package com.lcc.goshop.manager.mapper;

import com.lcc.goshop.manager.pojo.Permission;
import com.lcc.goshop.manager.pojo.Role;
import com.lcc.goshop.manager.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lcc on 2017/2/11.
 */
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User findByLoginName(String loginName);

    User findOfRoleOne(Long id);

    List<Permission> findPermissionListByUserId(Long userId);

    int findByLoginNameCount(String loginName);

    void updateLoginInfo(User user);

    List<Role> findByRole(Long userId);

    int findByRoleCount(@Param("userId")Long userId, @Param("roleName")String roleName);
}
