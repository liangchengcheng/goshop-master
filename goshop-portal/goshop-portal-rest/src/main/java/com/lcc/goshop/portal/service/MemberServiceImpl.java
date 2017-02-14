package com.lcc.goshop.portal.service;

import com.lcc.goshop.commons.exception.MapperException;
import com.lcc.goshop.commons.utils.RandomUtils;
import com.lcc.goshop.commons.utils.RegexValidateUtil;
import com.lcc.goshop.manager.mapper.FindPasswordMapper;
import com.lcc.goshop.manager.mapper.MemberMapper;
import com.lcc.goshop.manager.mapper.UserMapper;
import com.lcc.goshop.manager.pojo.Member;
import com.lcc.goshop.manager.pojo.User;
import com.lcc.goshop.portal.i.MemberService;
import com.lcc.goshop.shiro.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by lcc on 2017/2/14.
 */
@Service("memberService")
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordService passwordService;

    @Autowired
    FindPasswordService findPasswordService;

    @Autowired
    EMailService eMailService;

    @Autowired
    FindPasswordMapper findPasswordMapper;

    public int saveMember(Member member, User user) {
        //判断用户是否符合规范
        Assert.isTrue(RegexValidateUtil.checkLoginUser(user.getLoginName()) == false, "登录名不能为空，并且在字母开头2~9为数字或字母的组合！");
        //判断登录名是否重复
        Assert.isTrue(this.checkLoginName(user.getLoginName()), "登录名已经存在！");
        //判断邮件是否符合规范
        Assert.isTrue(RegexValidateUtil.checkEmail(member.getMemberEmail()), "邮件填写错误!");
        //判断邮件是否重复
        Assert.isTrue(this.checkEmail(member.getMemberEmail()), "此邮件已经注册！");
        //验证密码格式
        Assert.isTrue(RegexValidateUtil.checkPassword(user.getPassword()), "以字母开头,长度在6~18之间,只能包含字符、数字和下划线!");
        //密码加密、设置盐值
        user = this.passWordUser(user);
        user = this.setDefaultUser(user);
        userMapper.insert(user);
        member = this.setDefaultMember(member, user.getId());
        return memberMapper.insert(member);
    }

    public Boolean checkEmail(String memberEmail) {
        int count = memberMapper.findByMemberEmailCount(memberEmail);
        if (count > 0) {
            return false;
        }
        return true;
    }

    public Boolean checkLoginName(String loginName) {
        int count = userMapper.findByLoginNameCount(loginName);
        if (count > 0) {
            return false;
        }
        return true;
    }

    public Boolean checkLoginNameByEmail(String loginName, String email) {
        int count = memberMapper.checkLoginNameByEmail(loginName, email);
        if (count > 0) {
            return false;
        }
        return true;
    }

    public void sendEmailFindPassword(String username, String email) {
        String emailContent = findPasswordService.getContent(username);
        eMailService.send(email, "找回密码", emailContent);
    }

    public void updatePassword(String key, String password) throws Exception {
        findPasswordMapper.deleteInvalid();
        String loginName = findPasswordMapper.findByKey(key);
        if (StringUtils.hasText(loginName)) {
            User user = userMapper.findByLoginName(loginName);
            user.setPassword(password);
            userMapper.updateByPrimaryKey(passWordUser(user));
            findPasswordMapper.delete(key);
        } else {
            throw new Exception("此链接已过期!");
        }
    }

    public void updatePassword(Long userId, String password) throws Exception {
        User user = userMapper.selectByPrimaryKey(userId);
        user.setPassword(password);
        userMapper.updateByPrimaryKey(passWordUser(user));
    }

    public Member findUserByUserId(Long userId) {
        return memberMapper.findUserByUserId(userId);
    }

    public int update(Member member) {
        return memberMapper.updateByPrimaryKeySelective(member);
    }

    public int updateByUserId(Member member) {
        return memberMapper.updateByUserId(member);
    }

    public Boolean checkPassword(Long userId, String password) {
        User user = userMapper.selectByPrimaryKey(userId);
        Assert.notNull(user, "没有此用户！");
        String ciphertext = passwordService.encryptPassword(password, user.getSalt());
        if (ciphertext.equals(user.getPassword())) {
            return true;
        }
        return false;
    }

    public int updateEmail(Long userId, String email) {
        if (checkEmail(email)) {
            return memberMapper.updateEmail(userId, email);
        } else {
            throw new MapperException("邮件已被使用！");
        }
    }

    public int saveAvatar(Long userId, String memberAvatar) {
        return memberMapper.saveAvatar(userId, memberAvatar);
    }

    /**
     * 将密码加密
     */
    private User passWordUser(User user) {

        if (StringUtils.hasText(user.getPassword())) {
            String salt = RandomUtils.generateString(5);
            user.setPassword(passwordService.encryptPassword(user.getPassword(), salt));
            user.setSalt(salt);
        }
        return user;
    }

    public User setDefaultUser(User user) {
        user.setUpcreated(new Timestamp(System.currentTimeMillis()));
        user.setCreated(new Timestamp(System.currentTimeMillis()));
        user.setType(1);
        user.setEnable(1);
        return user;
    }

    public Member setDefaultMember(Member member, Long userId) {
        member.setUserId(userId);
        member.setMemberTime(new Timestamp(System.currentTimeMillis()));
        //是否允许举报(1可以/2不可以)
        member.setInformAllow(1);
        //会员是否有购买权限 1为开启 0为关闭
        member.setIsBuy(1);
        //会员是否有咨询和发送站内信的权限 1为开启 0为关闭
        member.setIsAllowtalk(1);
        //会员的开启状态 1为开启 0为关闭
        member.setMemberState(1);
        //会员积分
        member.setMemberPoints(0);
        //预存款可用金额
        member.setAvailablePredeposit(new BigDecimal(0));
        //预存款冻结金额
        member.setFreezePredeposit(new BigDecimal(0));
        //会员信用
        member.setMemberCredit(0);
        return member;
    }
}
