package com.lcc.goshop.manager.i;

import com.lcc.goshop.manager.pojo.Permission;
import com.lcc.goshop.manager.pojo.Role;
import com.lcc.goshop.manager.pojo.User;
import java.util.List;

/**
 * Created by lcc on 2017/2/10.
 */
public interface UserService {

    /**
     * 添加用户
     */
    int save(User user);

    //int updateByPrimaryKey(User user);

    int updateByPrimaryKeySelective(User user);

    User findOne(Long userId);

    User findByLoginName(String loginName);

    int delete(Long userId);

    User findOfRoleOne(Long userId);

    List<Permission> findPermissionListByUserId(Long userId);

    void updateLoginInfo(User user, String ip);

    List<Role> findByRole(Long userId);
}
