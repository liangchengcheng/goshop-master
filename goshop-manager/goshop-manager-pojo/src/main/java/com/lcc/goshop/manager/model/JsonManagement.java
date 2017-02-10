package com.lcc.goshop.manager.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcc on 2017/2/10.
 */
public class JsonManagement {
    private List<JsonManagementClass> jmcs = new ArrayList<JsonManagementClass>();

    public List<JsonManagementClass> getJmcs() {
        return jmcs;
    }

    public void setJmcs(List<JsonManagementClass> jmcs) {
        this.jmcs = jmcs;
    }
}
