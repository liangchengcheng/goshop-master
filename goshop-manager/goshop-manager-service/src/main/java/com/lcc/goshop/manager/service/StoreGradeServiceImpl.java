package com.lcc.goshop.manager.service;

import com.github.pagehelper.PageInfo;
import com.lcc.goshop.commons.exception.MapperException;
import com.lcc.goshop.commons.utils.PageUtils;
import com.lcc.goshop.manager.i.StoreGradeService;
import com.lcc.goshop.manager.mapper.StoreGradeMapper;
import com.lcc.goshop.manager.pojo.StoreGrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import java.util.List;

/**
 * Created by lcc on 2017/2/11.
 */
@Service("storeGradeService")
public class StoreGradeServiceImpl implements StoreGradeService {

    @Autowired
    StoreGradeMapper storeGradeMapper;

    @Override
    public PageInfo<StoreGrade> findAll(Integer curPage, Integer pageSize) {
        PageUtils.startPage(curPage,pageSize);
        List<StoreGrade> list = storeGradeMapper.findAll();
        //3、取分页后结果
        PageInfo<StoreGrade> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int save(StoreGrade storeGrade) {
        Assert.isTrue(checkBySgIdSgName(storeGrade.getSgName(), storeGrade.getSgId()),"名称已经存在！");
        Assert.isTrue(checkBySgIdSgSort(storeGrade.getSgSort(), storeGrade.getSgId()),"等级已经存在！");

        if(storeGrade.getSgId()!=null){
            return storeGradeMapper.updateByPrimaryKeySelective(storeGrade);
        }
        return storeGradeMapper.insert(storeGrade);
    }

    @Override
    public StoreGrade findOne(Integer id) {
        return storeGradeMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(Integer[] ids) {
        for(Integer id:ids) {
            storeGradeMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public boolean checkBySgIdSgName(String sgName, Integer sgId) {
        List<StoreGrade> list=storeGradeMapper.findBySgName(sgName);
        if(list.size()>1){
            throw new MapperException("店铺等级中名称重复数据异常！");
        }else if(list.size()==1){
            if(sgId==null){
                return false;
            }else if(list.get(0).getSgId()!=sgId){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkBySgIdSgSort(Integer sgSort, Integer sgId) {
        List<StoreGrade> list=storeGradeMapper.checkBySgIdSgSort(sgSort);
        if(list.size()>1){
            throw new MapperException("店铺等级中等级重复数据异常！");
        }else if(list.size()==1){
            if(sgId==null){
                return false;
            }else if(list.get(0).getSgId()!=sgId){
                return false;
            }
        }
        return true;
    }

    @Override
    public void delete(Integer id) {
        storeGradeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo<StoreGrade> findBySgName(String sgName, Integer curPage, Integer pageSize) {
        PageUtils.startPage(curPage,pageSize);
        List<StoreGrade> list = storeGradeMapper.findByLikeSgName(sgName);
        //3、取分页后结果
        PageInfo<StoreGrade> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public List<StoreGrade> findAll() {
        return  storeGradeMapper.findAll();
    }
}
