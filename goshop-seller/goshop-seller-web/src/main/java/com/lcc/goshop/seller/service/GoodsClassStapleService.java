package com.lcc.goshop.seller.service;

import com.lcc.goshop.manager.pojo.GoodsClass;
import com.lcc.goshop.manager.pojo.User;
import com.lcc.goshop.seller.modle.JsonGoodsClass;
import com.lcc.goshop.seller.pojo.GoodsClassStaple;
import java.util.List;

/**
 * Created by lcc on 2017/2/15.
 */
public interface GoodsClassStapleService {
    List<GoodsClassStaple> findByMemberId(Long memberId);

    GoodsClassStaple findOne(Integer id);

    int checkSaveStaple(User user, GoodsClass goodsClass);

    int delete(Integer stapleId);

    JsonGoodsClass selectGoodsClassStaple(Integer stapleId);
}
