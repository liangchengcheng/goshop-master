package com.lcc.goshop.seller.mapper;

import com.lcc.goshop.seller.pojo.GoodsClassStaple;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lcc on 2017/2/15.
 */
public interface GoodsClassStapleMapper {
    int deleteByPrimaryKey(Integer stapleId);

    int insert(GoodsClassStaple record);

    int insertSelective(GoodsClassStaple record);

    GoodsClassStaple selectByPrimaryKey(Integer stapleId);

    int updateByPrimaryKeySelective(GoodsClassStaple record);

    int updateByPrimaryKey(GoodsClassStaple record);

    List<GoodsClassStaple> findByMemberId(@Param("memberId")Long memberId);

    GoodsClassStaple findOneByGcId3AndMemberId(@Param("gcId3")Integer gcId3, @Param("memberId")Long memberId);
}
