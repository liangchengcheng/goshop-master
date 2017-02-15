package com.lcc.goshop.seller.service;

import com.github.pagehelper.PageInfo;
import com.lcc.goshop.manager.pojo.AlbumClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lcc on 2017/2/15.
 */
@Service
public class AlbumClassServiceImpl implements AlbumClassService {

    public PageInfo<AlbumClass> findByStoreId(Integer curPage, Integer pageSize, Long memberId, Integer sortValue) {
        return null;
    }

    public Integer findCountByStoreId(Long userId) {
        return null;
    }

    public AlbumClass findOneByAclassNameAndStoreId(String aclassName, Long userId) {
        return null;
    }

    public int save(AlbumClass albumClass, Long userId) {
        return 0;
    }

    public int delete(Integer aclassId, Long userId) {
        return 0;
    }

    public AlbumClass findByAclassIdAndUserId(Integer aclassId, Long userId) {
        return null;
    }

    public int update(AlbumClass albumClass, Long userId) {
        return 0;
    }
}
