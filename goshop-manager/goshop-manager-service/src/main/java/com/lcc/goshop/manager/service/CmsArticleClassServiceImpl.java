package com.lcc.goshop.manager.service;

import com.github.pagehelper.PageInfo;
import com.lcc.goshop.commons.exception.MapperException;
import com.lcc.goshop.commons.utils.PageUtils;
import com.lcc.goshop.manager.i.CmsArticleClassService;
import com.lcc.goshop.manager.mapper.CmsArticleClassMapper;
import com.lcc.goshop.manager.pojo.CmsArticleClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by lcc on 2017/2/11.
 */
@Service("cmsArticleClassService")
public class CmsArticleClassServiceImpl implements CmsArticleClassService {
    @Autowired
    CmsArticleClassMapper cmsArticleClassMapper;

    public PageInfo<CmsArticleClass> findGradeByParentId(Integer curPage, Integer pageSize, Long parentId) {
        PageUtils.startPage(curPage,pageSize);
        //2、查询结果
        List<CmsArticleClass> list=cmsArticleClassMapper.findGradeByParentId(parentId);
        //3、取分页后结果
        PageInfo<CmsArticleClass> pageInfo = new PageInfo<CmsArticleClass>(list);
        return pageInfo;
    }

    public List<CmsArticleClass> findByParentId(Long parentId) {
        return cmsArticleClassMapper.findByParentId(parentId);
    }

    public int save(CmsArticleClass cmsArticleClass) {
        return cmsArticleClassMapper.insertSelective(cmsArticleClass);
    }

    public CmsArticleClass findOne(Long classId) {
        return cmsArticleClassMapper.selectByPrimaryKey(classId);
    }

    public int update(CmsArticleClass cmsArticleClass) {
        return cmsArticleClassMapper.updateByPrimaryKeySelective(cmsArticleClass);
    }

    public boolean checkByIdNameParentId(Long classId, String className, Long parentId) {
        List<CmsArticleClass> list = cmsArticleClassMapper.findByNameParentId(className,parentId);
        if(list.size()>1){
            throw new MapperException("数据异常");
        }else if(list.size()==1){
            if(classId==null){
                return false;
            }else if(list.get(0).getClassId()!=classId){
                return false;
            }
        }
        return true;
    }

    public int updateClassSort(Long id, String value) {
        Assert.notNull(id, "id不能为空！");
        Assert.hasText(value,"排序值不能为空！");
        return cmsArticleClassMapper.updateClassSort(id, Integer.valueOf(value));
    }

    public int updateClassName(Long id, String value) {
        Assert.notNull(id,"id不能为空！");
        Assert.hasText(value,"名称不能为空！");
        return cmsArticleClassMapper.updateClassName(id, value);
    }

    public int delete(Long classId) {
        return  cmsArticleClassMapper.deleteByPrimaryKey(classId);
    }

    public void delete(Long[] classIds) {
        for(Long id:classIds){
            this.delete(id);
        }
    }

    public List<CmsArticleClass> findTreeByParentId(Long parentId) {
        return cmsArticleClassMapper.findTreeByParentId(parentId);
    }
}
