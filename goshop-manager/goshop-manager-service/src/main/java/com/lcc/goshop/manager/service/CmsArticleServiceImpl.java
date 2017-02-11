package com.lcc.goshop.manager.service;

import com.github.pagehelper.PageInfo;
import com.lcc.goshop.commons.utils.PageUtils;
import com.lcc.goshop.manager.i.CmsArticleService;
import com.lcc.goshop.manager.mapper.CmsArticleMapper;
import com.lcc.goshop.manager.pojo.CmsArticle;
import com.lcc.goshop.manager.pojo.CmsArticleWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lcc on 2017/2/11.
 */
@Service("cmsArticleService")
public class CmsArticleServiceImpl implements CmsArticleService {

    @Autowired
    CmsArticleMapper cmsArticleMapper;

    public PageInfo<CmsArticle> findBaseByArticleState(Integer curPage, Integer pageSize, Integer articleState) {
        PageUtils.startPage(curPage,pageSize);
        List<CmsArticle> list = cmsArticleMapper.findBaseByArticleState(articleState);
        return new PageInfo<CmsArticle>(list);
    }

    public PageInfo<CmsArticle> query(Integer curPage, Integer pageSize, Integer articleState, String articleTitle, String articlePublisherName) {
        PageUtils.startPage(curPage,pageSize);
        List<CmsArticle> list = cmsArticleMapper.query(articleState,articleTitle,articlePublisherName);
        return new PageInfo<CmsArticle>(list);
    }

    public int save(CmsArticleWithBLOBs cmsArticle) {
        return cmsArticleMapper.insertSelective(cmsArticle);
    }

    public int delete(Long articleId) {
        return cmsArticleMapper.deleteByPrimaryKey(articleId);
    }

    public CmsArticleWithBLOBs findOne(Long articleId) {
        return cmsArticleMapper.selectByPrimaryKey(articleId);
    }

    public int update(CmsArticleWithBLOBs cmsArticle) {
        return cmsArticleMapper.updateByPrimaryKeySelective(cmsArticle);
    }

    public int updateByArticleCommendFlag(Long articleId, Integer articleCommendFlag) {
        return cmsArticleMapper.updateByArticleCommendFlag(articleId,articleCommendFlag);
    }

    public int updateByArticleCommendImageFlag(Long articleId, Integer articleCommendImageFlag) {
        return cmsArticleMapper.updateByArticleCommendImageFlag(articleId, articleCommendImageFlag);
    }

    public int updateByArticleCommentFlag(Long articleId, Integer articleCommentFlag) {
        return cmsArticleMapper.updateByArticleCommentFlag(articleId, articleCommentFlag);
    }

    public int updateByArticleAttitudeFlag(Long articleId, Integer articleAttitudeFlag) {
        return cmsArticleMapper.updateByArticleAttitudeFlag(articleId, articleAttitudeFlag);
    }

    public int updateByArticleSort(Long articleId, Integer articleSort) {
        return cmsArticleMapper.updateByArticleSort(articleId,articleSort);
    }

    public int updateByArticleClick(Long articleId, Integer articleClick) {
        return cmsArticleMapper.updateByArticleClick(articleId,articleClick);
    }
}
