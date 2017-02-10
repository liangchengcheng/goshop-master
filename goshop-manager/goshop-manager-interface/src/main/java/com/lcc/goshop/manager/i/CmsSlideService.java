package com.lcc.goshop.manager.i;

import com.lcc.goshop.manager.pojo.CmsSlide;

import java.util.List;

/**
 * Created by lcc on 2017/2/10.
 */
public interface CmsSlideService {
    List<CmsSlide> findAll();

    void delete(Integer slideId);

    CmsSlide save(Integer slideSort, String slidePath);

    CmsSlide update(Integer slideId, Integer slideSort, String slidePath);

    void update(Integer[] ids, String[] imageUrl);
}
