package com.lcc.goshop.manager.service;

import com.github.pagehelper.PageInfo;
import com.lcc.goshop.commons.utils.PageUtils;
import com.lcc.goshop.manager.i.ArticleLangService;
import com.lcc.goshop.manager.mapper.ArticleLangInfoMapper;
import com.lcc.goshop.manager.mapper.ArticleLangMainMapper;
import com.lcc.goshop.manager.pojo.ArticleLangInfo;
import com.lcc.goshop.manager.pojo.ArticleLangMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service("articleLangService")
public class ArticleLangServiceImpl implements ArticleLangService {

    @Autowired
    ArticleLangInfoMapper articleLangInfoMapper;

    @Autowired
    ArticleLangMainMapper articleLangMainMapper;


    public PageInfo<ArticleLangMain> findManyAll(Integer curPage, Integer pageSize) {
        PageUtils.startPage(curPage,pageSize);
        List<ArticleLangMain> list =articleLangMainMapper.findManyAll();
        return new PageInfo<ArticleLangMain>(list);
    }

    public PageInfo<ArticleLangMain> queryMany(Integer curPage, Integer pageSize, String articleTitle, String articlePublisherName, Integer articleState, Long articleClassId) {
        PageUtils.startPage(curPage,pageSize);
        List<ArticleLangMain> list =articleLangMainMapper.queryMany(articleTitle,articlePublisherName,articleState,articleClassId);
        return new PageInfo<ArticleLangMain>(list);
    }

    public PageInfo<ArticleLangMain> findManyByArticleClassId(Integer curPage, Integer pageSize, Long articleClassId) {
        PageUtils.startPage(curPage,pageSize);
        List<ArticleLangMain> list = articleLangMainMapper.findManyByArticleClassId(articleClassId);
        return new PageInfo<ArticleLangMain>(list);
    }

    public PageInfo<ArticleLangMain> findPublishManyByArticleClassId(Integer curPage, Integer pageSize, Long articleClassId, String year, String langType) {
        PageUtils.startPage(curPage,pageSize);
        List<ArticleLangMain> list = articleLangMainMapper.findPublishManyByArticleClassId(articleClassId,year,langType);
        return new PageInfo<ArticleLangMain>(list);
    }

    public PageInfo<ArticleLangMain> findRetrenchPublishManyByArticleClassId(Integer curPage, Integer pageSize, Long articleClassId, String year, String langType) {
        PageUtils.startPage(curPage,pageSize);
        List<ArticleLangMain> list = articleLangMainMapper.findRetrenchPublishManyByArticleClassId(articleClassId,year,langType);
        return new PageInfo<ArticleLangMain>(list);
    }

    public PageInfo<ArticleLangMain> findRetrenchImagePublishManyByArticleClassId(Integer curPage, Integer pageSize, Long articleClassId, String year, String langType) {
        PageUtils.startPage(curPage,pageSize);
        List<ArticleLangMain> list = articleLangMainMapper.findRetrenchImagePublishManyByArticleClassId(articleClassId,year,langType);
        return new PageInfo<ArticleLangMain>(list);
    }

    public int save(ArticleLangMain articleLang, List<ArticleLangInfo> articleLangInfoList) {
        articleLangMainMapper.insertSelective(articleLang);
        for(ArticleLangInfo o:articleLangInfoList){
            o.setArticleId(articleLang.getArticleId());
            articleLangInfoMapper.insertSelective(o);
        }
        return 1;
    }

    public ArticleLangMain findManyOne(Long articleId) {
        return articleLangMainMapper.findManyOne(articleId);
    }

    public int update(ArticleLangMain articleLang, List<ArticleLangInfo> articleLangInfoList) {
        articleLangMainMapper.updateByPrimaryKeySelective(articleLang);
        for(ArticleLangInfo o:articleLangInfoList){
            articleLangInfoMapper.updateByPrimaryKeySelective(o);
        }
        return 1;
    }

    public ArticleLangMain findMainOne(Long articleId) {
        return articleLangMainMapper.selectByPrimaryKey(articleId);
    }

    public int update(ArticleLangMain articleLangMain) {
        return articleLangMainMapper.updateByPrimaryKeySelective(articleLangMain);
    }

    public int delete(Long articleId) {
        articleLangInfoMapper.deleteByArticleId(articleId);
        return articleLangMainMapper.deleteByPrimaryKey(articleId);
    }

    public int updateByArticleSort(Long articleId, Integer articleSort) {
        return articleLangMainMapper.updateByArticleSort(articleId,articleSort);
    }
}