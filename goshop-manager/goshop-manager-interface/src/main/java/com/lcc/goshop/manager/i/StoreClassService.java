package com.lcc.goshop.manager.i;

import com.github.pagehelper.PageInfo;
import com.lcc.goshop.manager.pojo.StoreClass;

import java.util.List;

/**
 * Created by lcc on 2017/2/10.
 */
public interface StoreClassService {
    List<StoreClass> findAllOrderBySort();

    int save(StoreClass storeClass);

    /**
     * 校验是否存在此分类名称
     */
    boolean checkByNameParentId(String name,Long parentId);

    PageInfo<StoreClass> findTreePageByParentId(Integer curPage, Integer pageSize, Long parentId);

    StoreClass findOne(Long id);

    boolean checkByIdNameParentId(Long id, String name, Long parentId);

    int update(StoreClass storeClass);

    int updateSort(Long id, String value);

    int updateName(Long id, String value);

    List<StoreClass> findByParentId(Long parentId);

    int delete(Long id);

    void delete(Long[] ids);

    List<StoreClass> findTreeByParentId(Long parentId);

    PageInfo<StoreClass> findGradeByParentId(Integer curPage,Integer pageSize,Long parentId);
}
