package com.lcc.goshop.manager.i;

import com.github.pagehelper.PageInfo;
import com.lcc.goshop.manager.pojo.CmsArticle;
import com.lcc.goshop.manager.pojo.CmsArticleWithBLOBs;

/**
 * Created by lcc on 2017/2/10.
 */
public interface CmsArticleService {
    //草稿箱
    public static final Integer TYPE_DRAFT = 1;
    //审核箱
    public static final Integer TYPE_VERIFY = 2;
    //发布箱
    public static final Integer TYPE_PUBLISH = 3;
    //垃圾箱
    public static final Integer TYPE_RUBBISH = 4;

    /**
     * 按文件类型查询
     */
    PageInfo<CmsArticle> findBaseByArticleState(Integer curPage, Integer pageSize, Integer articleState);

    PageInfo<CmsArticle> query(Integer curPage, Integer pageSize, Integer articleState, String articleTitle, String articlePublisherName);

    int save(CmsArticleWithBLOBs cmsArticle);

    int delete(Long articleId);

    CmsArticleWithBLOBs findOne(Long articleId);

    int update(CmsArticleWithBLOBs cmsArticle);

    /**
     * 更新文章推荐标志
     */
    int updateByArticleCommendFlag(Long articleId, Integer articleCommendFlag);

    int updateByArticleCommendImageFlag(Long articleId, Integer articleCommendImageFlag);

    int updateByArticleCommentFlag(Long articleId, Integer articleCommentFlag);

    int updateByArticleAttitudeFlag(Long articleId, Integer articleAttitudeFlag);

    int updateByArticleSort(Long articleId, Integer articleSort);

    int updateByArticleClick(Long articleId, Integer articleClick);
}
