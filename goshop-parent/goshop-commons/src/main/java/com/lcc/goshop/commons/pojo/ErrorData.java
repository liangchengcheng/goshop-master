package com.lcc.goshop.commons.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcc on 2017/2/7.
 */
@XmlRootElement(name="data")
@XmlAccessorType (XmlAccessType.FIELD)
public class ErrorData {

    @XmlElement
    private List<ErrorMessage> action = new ArrayList<ErrorMessage>();

    public List<ErrorMessage> getAction() {
        return action;
    }

    public void setAction(List<ErrorMessage> action) {
        this.action = action;
    }


}