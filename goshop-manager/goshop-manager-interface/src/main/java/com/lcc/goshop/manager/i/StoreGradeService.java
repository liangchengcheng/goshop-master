package com.lcc.goshop.manager.i;

import com.github.pagehelper.PageInfo;
import com.lcc.goshop.manager.pojo.StoreGrade;

import java.util.List;

/**
 * Created by lcc on 2017/2/10.
 */
public interface StoreGradeService {
    PageInfo<StoreGrade> findAll(Integer curPage, Integer pageSize);

    int save(StoreGrade storeGrade);

    StoreGrade findOne(Integer id);

    void delete(Integer[] ids);

    boolean checkBySgIdSgName(String sgName, Integer sgId);

    boolean checkBySgIdSgSort(Integer sgSort, Integer sgId);

    void delete(Integer id);

    PageInfo<StoreGrade> findBySgName(String sgName, Integer curPage, Integer pageSize);

    List<StoreGrade> findAll();
}
