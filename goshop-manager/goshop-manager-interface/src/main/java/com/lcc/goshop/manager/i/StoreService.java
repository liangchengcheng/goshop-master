package com.lcc.goshop.manager.i;

import com.github.pagehelper.PageInfo;
import com.lcc.goshop.manager.pojo.Store;
import com.lcc.goshop.manager.pojo.StoreJoin;
import com.lcc.goshop.manager.pojo.StoreWithBLOBs;
import com.lcc.goshop.manager.pojo.User;

/**
 * Created by lcc on 2017/2/10.
 */
public interface StoreService {
    PageInfo<Store> findAll(Integer curPage, Integer size);

    /**
     * 申请开店
     */
    int openStore(StoreJoin storeJoin);

    int save(StoreWithBLOBs store);

    Store getCurrentStore(User user);

    PageInfo<Store> find(Integer gradeId, String sellerName, String storeType, String storeName, Integer curPage, Integer size);

    Store findOne(Integer storeId);

    void update(StoreWithBLOBs store);

    Store findByMemberId(Long userId);
}
