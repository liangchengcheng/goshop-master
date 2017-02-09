package com.lcc.goshop.solr.service;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.RowBounds;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by lcc on 2017/2/9.
 */
public class CloudSolrServerImpl extends SolrServiceParent {

    private String urlPath;

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    @Override
    public SolrClient getHttpSolrClient(String core) {
        //参数：zookeeper服务器的地址列表。
        CloudSolrClient server = new CloudSolrClient(urlPath);
        //指定默认连接的collection
        server.setDefaultCollection(core);
        return server;
    }
}
