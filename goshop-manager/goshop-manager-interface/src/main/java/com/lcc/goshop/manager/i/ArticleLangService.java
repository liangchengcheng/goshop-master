package com.lcc.goshop.manager.i;

import com.github.pagehelper.PageInfo;
import com.lcc.goshop.manager.pojo.ArticleLangInfo;
import com.lcc.goshop.manager.pojo.ArticleLangMain;
import java.util.List;

/**
 * Created by lcc on 2017/2/10.
 */
public interface ArticleLangService {

    PageInfo<ArticleLangMain> findManyAll(Integer curPage, Integer pageSize);

    PageInfo<ArticleLangMain> queryMany(Integer curPage, Integer pageSize, String articleTitle, String articlePublisherName, Integer articleState, Long articleClassId);

    PageInfo<ArticleLangMain> findManyByArticleClassId(Integer curPage, Integer pageSize, Long articleClassId);

    /**
     * 查询已发布文章
     */
    PageInfo<ArticleLangMain> findPublishManyByArticleClassId(Integer curPage, Integer pageSize, Long articleClassId, String year, String langType);

    /**
     * 查询已发布文章,精简版，用于列表查询
     */
    PageInfo<ArticleLangMain> findRetrenchPublishManyByArticleClassId(Integer curPage, Integer pageSize, Long articleClassId, String year, String langType);

    /**
     * 查询图片新闻
     */
    PageInfo<ArticleLangMain> findRetrenchImagePublishManyByArticleClassId(Integer curPage, Integer pageSize, Long articleClassId, String year, String langType);

    int save(ArticleLangMain articleLang, List<ArticleLangInfo> articleLangInfoList);

    ArticleLangMain findManyOne(Long articleId);

    int update(ArticleLangMain articleLang, List<ArticleLangInfo> articleLangInfoList);

    ArticleLangMain findMainOne(Long articleId);

    int update(ArticleLangMain articleLangMain);

    int delete(Long articleId);

    int updateByArticleSort(Long articleId, Integer articleSort);
}
