package com.lcc.goshop.solr.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.RowBounds;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * Created by lcc on 2017/2/9.
 */
public interface SolrService {

    /**
     * 获取域客户端
     */
    SolrClient getHttpSolrClient(String core);

    /**
     * 增加单个实体
     *
     * @param core 域名
     * @param bean 实体
     * @throws IOException
     * @throws SolrServerException
     */
    void add(String core, Object bean) throws IOException, SolrServerException;

    /**
     * 增加一个集合
     *
     * @param core  域名
     * @param beans 实体集合
     * @throws IOException
     * @throws SolrServerException
     */
    void adds(String core, Collection<?> beans) throws IOException, SolrServerException;

    /**
     * 查询list
     *
     * @param core  域名
     * @param clazz 查询类class
     * @param query
     * @param <T>
     * @throws IOException
     * @throws SolrServerException
     */
    <T> List<T> query(String core, Class<T> clazz, SolrQuery query) throws IOException, SolrServerException;

    QueryResponse query(String core, SolrQuery query) throws IOException, SolrServerException;

    <T> PageInfo query(String core, Class<T> clazz, SolrQuery query, RowBounds rowBounds) throws IOException, SolrServerException;

    PageInfo query(String core, SolrQuery query, RowBounds rowBounds) throws IOException, SolrServerException;

    PageInfoFacet queryFacet(String core, SolrQuery query, RowBounds rowBounds) throws IOException, SolrServerException;
}
