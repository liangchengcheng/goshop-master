package com.lcc.goshop.manager.mapper;

import com.lcc.goshop.manager.pojo.GoodsType;

import java.util.List;

/**
 * Created by lcc on 2017/2/11.
 */
public interface GoodsTypeMapper {
    int deleteByPrimaryKey(Integer typeId);

    int insert(GoodsType record);

    int insertSelective(GoodsType record);

    GoodsType selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(GoodsType record);

    int updateByPrimaryKey(GoodsType record);

    List<GoodsType> findAll();
}
