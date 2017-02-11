package com.lcc.goshop.manager.service;

import com.lcc.goshop.manager.i.AdminService;
import com.lcc.goshop.manager.mapper.RoleMapper;
import com.lcc.goshop.manager.mapper.UserMapper;
import com.lcc.goshop.manager.mapper.UserRoleMapper;
import com.lcc.goshop.manager.pojo.Role;
import com.lcc.goshop.manager.pojo.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lcc on 2017/2/11.
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {
    private final static String ADMIN_ROLE="admin";

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    public Integer getIsAdmin(Long userId) {
        return userMapper.findByRoleCount(userId, ADMIN_ROLE);
    }

    public void setAdmin(Long userId,Integer isAdmin){
        if (userId == 0){
            return;
        }

        Role role = roleMapper.findByName(ADMIN_ROLE);
        UserRole userRole = userRoleMapper.findByUIdAndRId(userId, role.getId());
        if (isAdmin != null&& isAdmin ==1){
            if (userRole == null){
                userRole = new UserRole();
                userRole.setuId(userId);
                userRole.setrId(role.getId());
                userRoleMapper.insert(userRole);
            }
        }else {
            if (userRole != null){
                userRoleMapper.deleteByUIdAndRId(userId, role.getId());
            }
        }
    }
}
