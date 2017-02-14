package com.lcc.goshop.portal.service;

import com.lcc.goshop.manager.pojo.StoreGoodsClass;

import java.util.List;

/**
 * Created by lcc on 2017/2/14.
 */
public interface StoreGoodsClassService {
    List<StoreGoodsClass> findByStcParentId(Integer id);
}
