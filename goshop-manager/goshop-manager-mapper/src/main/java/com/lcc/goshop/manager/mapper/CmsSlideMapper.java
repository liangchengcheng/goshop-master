package com.lcc.goshop.manager.mapper;

import com.lcc.goshop.manager.pojo.CmsSlide;

import java.util.List;

/**
 * Created by lcc on 2017/2/11.
 */
public interface CmsSlideMapper {

    int deleteByPrimaryKey(Integer slideId);

    int insert(CmsSlide record);

    int insertSelective(CmsSlide record);

    CmsSlide selectByPrimaryKey(Integer slideId);

    int updateByPrimaryKeySelective(CmsSlide record);

    int updateByPrimaryKey(CmsSlide record);

    List<CmsSlide> findAll();
}
