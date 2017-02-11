package com.lcc.goshop.manager.mapper;

import com.lcc.goshop.manager.pojo.StoreGrade;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lcc on 2017/2/11.
 */
public interface StoreGradeMapper {

    int deleteByPrimaryKey(Integer sgId);

    int insert(StoreGrade record);

    int insertSelective(StoreGrade record);

    StoreGrade selectByPrimaryKey(Integer sgId);

    int updateByPrimaryKeySelective(StoreGrade record);

    int updateByPrimaryKeyWithBLOBs(StoreGrade record);

    int updateByPrimaryKey(StoreGrade record);

    List<StoreGrade> findAll();

    List<StoreGrade> findBySgName(@Param("sgName")String sgName);

    List<StoreGrade> checkBySgIdSgSort(@Param("sgSort")Integer sgSort);

    List<StoreGrade> findByLikeSgName(@Param("sgName")String sgName);
}
