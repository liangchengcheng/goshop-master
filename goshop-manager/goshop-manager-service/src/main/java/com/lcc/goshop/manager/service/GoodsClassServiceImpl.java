package com.lcc.goshop.manager.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lcc.goshop.commons.exception.MapperException;
import com.lcc.goshop.commons.utils.PageUtils;
import com.lcc.goshop.manager.i.GoodsClassService;
import com.lcc.goshop.manager.mapper.GoodsClassMapper;
import com.lcc.goshop.manager.pojo.GoodsClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("goodsClassService")
public class GoodsClassServiceImpl implements GoodsClassService {

    @Autowired
    GoodsClassMapper goodsClassMapper;

    public List<GoodsClass> findTreeByGcParentId(Integer gcParentId) {
        return goodsClassMapper.findTreeByGcParentId(gcParentId);
    }

    public List<GoodsClass> findByGcParentId(Integer parentId) {
        if(parentId==null){
            parentId=0;
        }
        return goodsClassMapper.findByGcParentId(parentId);
    }

    public int save(GoodsClass goodsClass) {
        return goodsClassMapper.insert(goodsClass);
    }

    public boolean checkByIdNameParentId(Integer gcId, String gcName, Integer gcParentId) {
        List<GoodsClass> list = goodsClassMapper.findByGcNameGcParentId(gcName,gcParentId);
        if(list.size()>1){
            throw new MapperException("数据异常");
        }else if(list.size()==1){
            if(gcId==null){
                return false;
            }else if(list.get(0).getGcId()!=gcId){
                return false;
            }
        }
        return true;
    }

    public PageInfo<GoodsClass> findAll(Integer curPage, Integer pageSize) {
        PageUtils.startPage(curPage,pageSize);
        List<GoodsClass> list=goodsClassMapper.findAll();
        return new PageInfo<GoodsClass>(list);
    }

    public PageInfo<GoodsClass> findGradeByGcParentId(Integer gcParentId, Integer curPage, Integer pageSize) {
        PageUtils.startPage(curPage,pageSize);
        List<GoodsClass> list=goodsClassMapper.findGradeByGcParentId(gcParentId);
        return new PageInfo<GoodsClass>(list);
    }

    public List<GoodsClass> findGradeByGcParentId(Integer gcParentId) {
        return goodsClassMapper.findGradeByGcParentId(gcParentId);
    }

    public GoodsClass findOne(Integer gcId) {
        return goodsClassMapper.selectByPrimaryKey(gcId);
    }

    public int delete(Integer id) {
        return goodsClassMapper.deleteByPrimaryKey(id);
    }
}
