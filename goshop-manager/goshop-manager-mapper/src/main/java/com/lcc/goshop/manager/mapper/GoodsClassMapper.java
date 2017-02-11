package com.lcc.goshop.manager.mapper;

import com.lcc.goshop.manager.pojo.GoodsClass;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lcc on 2017/2/11.
 */
public interface GoodsClassMapper {
    int deleteByPrimaryKey(Integer gcId);

    int insert(GoodsClass record);

    int insertSelective(GoodsClass record);

    GoodsClass selectByPrimaryKey(Integer gcId);

    int updateByPrimaryKeySelective(GoodsClass record);

    int updateByPrimaryKey(GoodsClass record);

    List<GoodsClass> findTreeByGcParentId(Integer gcParentId);

    List<GoodsClass> findByGcParentId(@Param("gcParentId")Integer parentId);

    List<GoodsClass> findByGcNameGcParentId(@Param("gcName")String gcName, @Param("gcParentId")Integer gcParentId);

    List<GoodsClass> findAll();

    List<GoodsClass> findGradeByGcParentId(@Param("gcParentId")Integer gcParentId);
}
