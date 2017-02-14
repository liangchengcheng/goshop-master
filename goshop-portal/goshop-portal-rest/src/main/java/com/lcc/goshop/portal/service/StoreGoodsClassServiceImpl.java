package com.lcc.goshop.portal.service;

import com.lcc.goshop.manager.mapper.StoreGoodsClassMapper;
import com.lcc.goshop.manager.pojo.StoreGoodsClass;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * Created by lcc on 2017/2/14.
 */
public class StoreGoodsClassServiceImpl implements StoreGoodsClassService {

    @Autowired
    StoreGoodsClassMapper storeGoodsClassMapper;

    public List<StoreGoodsClass> findByStcParentId(Integer parentId) {
        return storeGoodsClassMapper.findByStcParentId(parentId);
    }
}
