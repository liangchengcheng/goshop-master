package com.lcc.goshop.manager.i;

import com.lcc.goshop.manager.pojo.GoodsType;

import java.util.List;

/**
 * Created by lcc on 2017/2/10.
 */
public interface GoodsTypeService {
    List<GoodsType> findAll();
}
