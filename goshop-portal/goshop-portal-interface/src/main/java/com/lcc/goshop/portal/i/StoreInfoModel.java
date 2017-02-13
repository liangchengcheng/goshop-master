package com.lcc.goshop.portal.i;

import com.lcc.goshop.manager.pojo.GoodsClass;
import com.lcc.goshop.manager.pojo.StoreClass;
import com.lcc.goshop.manager.pojo.StoreGrade;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lcc on 2017/2/13.
 */
public class StoreInfoModel implements Serializable{

    //店铺分类
    private List<StoreClass> storeClassParentList;
    //店铺等级
    private List<StoreGrade> storeGradeList;
    //经营类目
    private List<GoodsClass> goodsClassParentList;

    public List<StoreClass> getStoreClassParentList() {
        return storeClassParentList;
    }

    public void setStoreClassParentList(List<StoreClass> storeClassParentList) {
        this.storeClassParentList = storeClassParentList;
    }

    public List<StoreGrade> getStoreGradeList() {
        return storeGradeList;
    }

    public void setStoreGradeList(List<StoreGrade> storeGradeList) {
        this.storeGradeList = storeGradeList;
    }

    public List<GoodsClass> getGoodsClassParentList() {
        return goodsClassParentList;
    }

    public void setGoodsClassParentList(List<GoodsClass> goodsClassParentList) {
        this.goodsClassParentList = goodsClassParentList;
    }
}
