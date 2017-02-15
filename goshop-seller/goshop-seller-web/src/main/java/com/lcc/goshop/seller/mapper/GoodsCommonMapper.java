package com.lcc.goshop.seller.mapper;

import com.lcc.goshop.seller.pojo.GoodsCommon;
import com.lcc.goshop.seller.pojo.GoodsCommonWithBLOBs;

/**
 * Created by lcc on 2017/2/15.
 */
public interface GoodsCommonMapper {
    int deleteByPrimaryKey(Integer goodsCommonid);

    int insert(GoodsCommonWithBLOBs record);

    int insertSelective(GoodsCommonWithBLOBs record);

    GoodsCommonWithBLOBs selectByPrimaryKey(Integer goodsCommonid);

    int updateByPrimaryKeySelective(GoodsCommonWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(GoodsCommonWithBLOBs record);

    int updateByPrimaryKey(GoodsCommon record);
}
