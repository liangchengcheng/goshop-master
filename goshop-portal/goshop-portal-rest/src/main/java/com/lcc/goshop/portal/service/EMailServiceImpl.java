package com.lcc.goshop.portal.service;

import com.lcc.goshop.commons.email.SimpleMailSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by lcc on 2017/2/13.
 */
@Service
public class EMailServiceImpl implements EMailService {

    @Value("${EMAIL_ADDRESS}")
    private String eMailAddress;

    @Value("${EMAIL_PASSWORD}")
    private String eMailPassword;

    public void send(String email, String title, String emailContent) {
        SimpleMailSender sms =  new SimpleMailSender(eMailAddress,eMailPassword);
        try{
            sms.send(email,title,emailContent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
