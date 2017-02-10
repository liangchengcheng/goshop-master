package com.lcc.goshop.manager.i;

/**
 * Created by lcc on 2017/2/10.
 */
public interface AdminService {
    /**
     * 获取该用户是否为管理员，1为是
     */
    Integer getIsAdmin(Long userId);

    /**
     * 设置、取消管理员
     */
    void setAdmin(Long userId,Integer isAdmin);
}
