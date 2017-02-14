package com.lcc.goshop.portal.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.lcc.goshop.commons.utils.RandomUtils;
import com.lcc.goshop.manager.mapper.FindPasswordMapper;
import com.lcc.goshop.manager.pojo.FindPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Timestamp;
import java.text.MessageFormat;

/**
 * Created by lcc on 2017/2/13.
 */
@Service
public class FindPasswordEMailServiceImpl implements FindPasswordService {

    @Value("${EMAIL_ADDRESS}")
    private String eMailAddress;

    @Value("${SHOP_URL}")
    private String shopUrl;

    @Autowired
    FindPasswordMapper findPasswordMapper;

    private static String content = "<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" id=\"message-body-wrapper\"><tbody>" +
            "<tr><td width=\"0\" id=\"message-body-left-margin\"></td><td id=\"message-body\"><table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" id=\"paragraphs\"><tbody>" +
            "<tr><td style=\"padding:0 5% 18px;font:300 14px/18px 'Lucida Grande', Lucida Sans, Lucida Sans Unicode, sans-serif, Arial, Helvetica, Verdana, sans-serif;color:#333;\" class=\"paragraph\">{0}，您好：</td></tr>" +
            "<tr><td style=\"padding:0 5% 18px;font:300 14px/18px 'Lucida Grande', Lucida Sans, Lucida Sans Unicode, sans-serif, Arial, Helvetica, Verdana, sans-serif;color:#333;\" class=\"paragraph\">您最近为提出了密码重设请求。要完成此过程，请点按以下链接。</td></tr><tr><td style=\"padding:0 5% 18px;font:300 14px/18px 'Lucida Grande', Lucida Sans, Lucida Sans Unicode, sans-serif, Arial, Helvetica, Verdana, sans-serif;color:#333;\" class=\"paragraph\">" +
            "请复制以下链接修改密码：{1}</td></tr><tr><td style=\"padding:0 5% 18px;font:300 14px/18px 'Lucida Grande', Lucida Sans, Lucida Sans Unicode, sans-serif, Arial, Helvetica, Verdana, sans-serif;color:#333;\" class=\"paragraph\">如果您未提出此请求，可能是其他用户无意中输入了您的电子邮件地址，您的帐户仍然安全。如果您怀疑有未经授权的人员访问了您的帐户，您应尽快前往您的帐户页面, 更改您的密码。</td></tr><tr><td style=\"padding:0 5% 18px;font:300 14px/18px 'Lucida Grande', Lucida Sans, Lucida Sans Unicode, sans-serif, Arial, Helvetica, Verdana, sans-serif;color:#333;\" class=\"paragraph\">此致</td></tr><tr><td style=\"padding:18px 5% 51px;font:300 14px/18px 'Lucida Grande', Lucida Sans, Lucida Sans Unicode, sans-serif, Arial, Helvetica, Verdana, sans-serif;\" id=\"signature\">支持</td></tr></tbody></table></td></tr></tbody></table>";

    public String getContent(String username) {
        String key = RandomUtils.generateString(28);
        this.saveFindPassword(username,key);
        String url =shopUrl+"/password/update.html?key="+key;
        return MessageFormat.format(content, username, url);
    }

    public int saveFindPassword(String username, String key) {
        findPasswordMapper.deleteInvalid();
        FindPassword findPassword = new FindPassword();
        findPassword.setCreated(new Timestamp(System.currentTimeMillis()));
        findPassword.setRandom(key);
        findPassword.setLoginName(username);
        return findPasswordMapper.insert(findPassword);
    }
}
