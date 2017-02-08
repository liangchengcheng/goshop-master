package com.lcc.goshop.commons.email;

/**
 * Created by lcc on 2017/2/8.
 */
public class SimpleMail {

    private String subject;

    private Object content;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
