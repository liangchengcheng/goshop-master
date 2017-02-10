package com.lcc.goshop.manager.i;

import com.github.pagehelper.PageInfo;
import com.lcc.goshop.manager.pojo.GoodsClass;

import java.util.List;

/**
 * Created by lcc on 2017/2/10.
 */
public interface GoodsClassService {

    List<GoodsClass> findTreeByGcParentId(Integer gcParentId);

    List<GoodsClass> findByGcParentId(Integer gcParentId);

    int save(GoodsClass goodsClass);

    boolean checkByIdNameParentId(Integer gcId, String gcName, Integer gcParentId);

    PageInfo<GoodsClass> findAll(Integer curPage, Integer size);

    PageInfo<GoodsClass> findGradeByGcParentId(Integer gcParentId, Integer curPage, Integer size);

    List<GoodsClass> findGradeByGcParentId(Integer gcParentId);

    GoodsClass findOne(Integer gcId);

    int delete(Integer id);
}
