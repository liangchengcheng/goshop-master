package com.lcc.goshop.manager.service;

import com.lcc.goshop.manager.i.RoleService;
import com.lcc.goshop.manager.mapper.RoleMapper;
import com.lcc.goshop.manager.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lcc on 2017/2/11.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public int save(Role role) {
        return roleMapper.insert(role);
    }

    @Override
    public int updateByPrimaryKey(Role role) {
        return roleMapper.updateByPrimaryKey(role);
    }

    @Override
    public Role findOne(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public Role findByName(String name) {
        return roleMapper.findByName(name);
    }

    @Override
    public int delete(Long id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Role findOfUserOne(Long id) {
        return roleMapper.findOfUserOne(id);
    }
}
