package com.lcc.goshop.manager.mapper;

import com.lcc.goshop.manager.pojo.ArticleLangInfo;

/**
 * Created by lcc on 2017/2/11.
 */
public interface ArticleLangInfoMapper {
    int deleteByPrimaryKey(Long articleInfoId);

    int insert(ArticleLangInfo record);

    int insertSelective(ArticleLangInfo record);

    ArticleLangInfo selectByPrimaryKey(Long articleInfoId);

    int updateByPrimaryKeySelective(ArticleLangInfo record);

    int updateByPrimaryKeyWithBLOBs(ArticleLangInfo record);

    int updateByPrimaryKey(ArticleLangInfo record);

    int deleteByArticleId(Long articleId);
}
