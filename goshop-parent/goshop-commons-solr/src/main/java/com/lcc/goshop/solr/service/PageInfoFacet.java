package com.lcc.goshop.solr.service;

import com.github.pagehelper.PageInfo;
import org.apache.solr.client.solrj.response.FacetField;
import java.util.List;

/**
 * Created by lcc on 2017/2/9.
 */
public class PageInfoFacet {

    private PageInfo pageInfo;

    private List<FacetField> facetFieldList;

    public PageInfo getPageInfo(){
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo){
        this.pageInfo = pageInfo;
    }

    public List<FacetField> getFacetFieldList(){
        return facetFieldList;
    }

    public void setFacetFieldList(List<FacetField> facetFieldList){
        this.facetFieldList = facetFieldList;
    }
}
