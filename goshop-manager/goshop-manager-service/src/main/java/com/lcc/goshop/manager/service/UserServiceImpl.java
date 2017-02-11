package com.lcc.goshop.manager.service;

import com.lcc.goshop.commons.exception.MapperException;
import com.lcc.goshop.commons.utils.RandomUtils;
import com.lcc.goshop.manager.i.UserService;
import com.lcc.goshop.manager.mapper.UserMapper;
import com.lcc.goshop.manager.pojo.Permission;
import com.lcc.goshop.manager.pojo.Role;
import com.lcc.goshop.manager.pojo.User;
import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lcc.goshop.shiro.service.PasswordService;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by lcc on 2017/2/11.
 */

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    private PasswordService passwordService;

    @Override
    public int save(User user) {
        User userDataBase=this.findByLoginName(user.getLoginName());
        if(userDataBase!=null){
            throw new MapperException("登录名称重复");
        }
        return userMapper.insertSelective(passWordUser(user));
    }

    /*@Override
    public int updateByPrimaryKey(User user) {
        return userMapper.updateByPrimaryKey(passWordUser(user));
    }
*/
    @Override
    public int updateByPrimaryKeySelective(User user) {
        return userMapper.updateByPrimaryKeySelective(passWordUser(user));
    }

    @Override
    public User findOne(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public User findByLoginName(String loginName) {
        return userMapper.findByLoginName(loginName);
    }

    @Override
    public int delete(Long userId ){
        return userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public User findOfRoleOne(Long userId) {
        return userMapper.findOfRoleOne(userId);
    }

    @Override
    public List<Permission> findPermissionListByUserId(Long userId) {
        return userMapper.findPermissionListByUserId(userId);
    }

    @Override
    public void updateLoginInfo(User user, String ip) {
        User loginUserInfo = new User();
        loginUserInfo.setId(user.getId());
        loginUserInfo.setLoginNum(user.getLoginNum() + 1);
        loginUserInfo.setLoginIp(ip);
        loginUserInfo.setOldLoginIp(user.getLoginIp());
        loginUserInfo.setLoginTime(new Timestamp(System.currentTimeMillis()));
        loginUserInfo.setOldLoginTime(user.getLoginTime());
        userMapper.updateLoginInfo(loginUserInfo);
    }

    @Override
    public List<Role> findByRole(Long userId) {
        return userMapper.findByRole(userId);
    }


    /**
     * 将密码加密
     */
    private  User passWordUser(User user){

        if(StringUtils.hasText(user.getPassword())){
            String salt= RandomUtils.generateString(5);
            user.setPassword(passwordService.encryptPassword(user.getPassword(),salt));
            user.setSalt(salt);
        }
        return user;
    }
}
