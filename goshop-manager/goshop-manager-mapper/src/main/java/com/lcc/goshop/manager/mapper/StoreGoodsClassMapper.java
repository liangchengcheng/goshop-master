package com.lcc.goshop.manager.mapper;

import com.lcc.goshop.manager.pojo.StoreGoodsClass;

import java.util.List;

/**
 * Created by lcc on 2017/2/11.
 */
public interface StoreGoodsClassMapper {

    int deleteByPrimaryKey(Integer stcId);

    int insert(StoreGoodsClass record);

    int insertSelective(StoreGoodsClass record);

    StoreGoodsClass selectByPrimaryKey(Integer stcId);

    int updateByPrimaryKeySelective(StoreGoodsClass record);

    int updateByPrimaryKey(StoreGoodsClass record);

    List<StoreGoodsClass> findByStcParentId(Integer parentId);
}
