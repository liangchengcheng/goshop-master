package com.lcc.goshop.portal.i;

import com.lcc.goshop.manager.pojo.Member;
import com.lcc.goshop.manager.pojo.User;

/**
 * Created by lcc on 2017/2/13.
 */
public interface MemberService {
    /**
     * 保存用户数据
     */
    int saveMember(Member member,User user);

    /**
     * 检测邮件
     * @param memberEmail true为已存在这个邮件
     */
    Boolean checkEmail(String memberEmail);

    /**
     * 检测用户名
     * @param loginName true为登录名已经存在
     */
    Boolean checkLoginName(String loginName);

    /**
     * 检查用户下是否有此电子邮件
     */
    Boolean checkLoginNameByEmail(String loginName, String email);

    /**
     * 发送邮件找回密码
     */
    void sendEmailFindPassword(String username, String email);

    void updatePassword(String key,String password) throws Exception;

    void updatePassword(Long userId,String password) throws Exception;

    Member findUserByUserId(Long userId);

    int update(Member member);

    int updateByUserId(Member member);

    Boolean checkPassword(Long userId, String password);

    int updateEmail(Long userId, String email);

    int saveAvatar(Long userId, String memberAvatar);
}
