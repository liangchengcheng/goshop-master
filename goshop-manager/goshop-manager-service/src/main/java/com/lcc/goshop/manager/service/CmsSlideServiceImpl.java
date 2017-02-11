package com.lcc.goshop.manager.service;


import com.lcc.goshop.manager.i.CmsSlideService;
import com.lcc.goshop.manager.mapper.CmsSlideMapper;
import com.lcc.goshop.manager.pojo.CmsSlide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cmsSlideService")
public class CmsSlideServiceImpl implements CmsSlideService {

    @Autowired
    CmsSlideMapper cmsSlideMapper;

    public List<CmsSlide> findAll() {
        return cmsSlideMapper.findAll();
    }

    public void delete(Integer slideId) {
        cmsSlideMapper.deleteByPrimaryKey(slideId);
    }

    public CmsSlide save(Integer slideSort, String slidePath) {
        CmsSlide cmsSlide = new CmsSlide();
        cmsSlide.setSlideSort(slideSort);
        cmsSlide.setSlidePath(slidePath);
        cmsSlideMapper.insertSelective(cmsSlide);
        return cmsSlide;
    }

    public CmsSlide update(Integer slideId, Integer slideSort, String slidePath) {
        CmsSlide cmsSlide = new CmsSlide();
        cmsSlide.setSlideId(slideId);
        cmsSlide.setSlideSort(slideSort);
        cmsSlide.setSlidePath(slidePath);
        cmsSlideMapper.updateByPrimaryKeySelective(cmsSlide);
        return cmsSlide;
    }

    public void update(Integer[] ids, String[] imageUrl) {
        for(int i=0;i<ids.length;i++){
            CmsSlide cmsSlide = new CmsSlide();
            cmsSlide.setSlideId(ids[i]);
            cmsSlide.setSlideUrl(imageUrl[i]);
            cmsSlideMapper.updateByPrimaryKeySelective(cmsSlide);
        }
    }
}
