package com.lcc.goshop.manager.mapper;

import com.lcc.goshop.manager.pojo.ArticleLangMain;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lcc on 2017/2/11.
 */
public interface ArticleLangMainMapper {
    int deleteByPrimaryKey(Long articleId);

    int insert(ArticleLangMain record);

    int insertSelective(ArticleLangMain record);

    ArticleLangMain selectByPrimaryKey(Long articleId);

    int updateByPrimaryKeySelective(ArticleLangMain record);

    int updateByPrimaryKeyWithBLOBs(ArticleLangMain record);

    int updateByPrimaryKey(ArticleLangMain record);

    /**
     * 查询全部主从表信息
     */
    List<ArticleLangMain> findManyAll();

    ArticleLangMain findManyOne(Long articleId);

    List<ArticleLangMain> queryMany(@Param("articleTitle")String articleTitle, @Param("articleAuthor")String articleAuthor, @Param("articleState")Integer articleState, @Param("articleClassId")Long articleClassId);

    int updateByArticleSort(@Param("articleId")Long articleId, @Param("articleSort")Integer articleSort);

    List<ArticleLangMain> findManyByArticleClassId(Long articleClassId);

    List<ArticleLangMain> findPublishManyByArticleClassId(@Param("articleClassId")Long articleClassId,@Param("year")String year,@Param("langType")String langType);

    List<ArticleLangMain> findRetrenchPublishManyByArticleClassId(@Param("articleClassId")Long articleClassId,@Param("year")String year,@Param("langType")String langType);

    List<ArticleLangMain> findRetrenchImagePublishManyByArticleClassId(@Param("articleClassId")Long articleClassId,@Param("year")String year,@Param("langType")String langType);
}
