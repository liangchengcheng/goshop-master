package com.lcc.goshop.portal.i;

import com.lcc.goshop.manager.pojo.Store;
import com.lcc.goshop.manager.pojo.StoreJoin;
import com.lcc.goshop.manager.pojo.User;

/**
 * Created by lcc on 2017/2/13.
 */
public interface StoreJoinService {

    /**
     * 用户申请卖家
     */
    void applySeller(User user, StoreJoin storeJoin);

    /**
     * 第三步用户申请卖家，并返回所需的实体信息
     */
    StoreInfoModel applySellerThree(User user, StoreJoin storeJoin);

    boolean verificationSellerName(String sellerName, Long userId);

    boolean verificationStoreName(String storeName, Long userId);

    StoreJoin save(User user, StoreJoin storeJoin, String[] ids, String[] names);

    Store getCurrentStore(User user);

    StoreJoin getCurrentUserStoreJoin(User user);

    int paySave(StoreJoin storeJoin);
}
