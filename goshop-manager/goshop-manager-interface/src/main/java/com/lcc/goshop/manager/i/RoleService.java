package com.lcc.goshop.manager.i;

import com.lcc.goshop.manager.pojo.Role;

/**
 * Created by lcc on 2017/2/10.
 */
public interface RoleService {
    int save(Role role);

    int updateByPrimaryKey(Role role);

    Role findOne(Long id);

    Role findByName(String name);

    int delete(Long id);

    Role findOfUserOne(Long id);
}
