package com.lcc.goshop.commons.pojo;

import java.util.List;

/**
 * Created by lcc on 2017/2/7.
 */
public class EUDataGridResult {
    private long total;
    private List<?> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
