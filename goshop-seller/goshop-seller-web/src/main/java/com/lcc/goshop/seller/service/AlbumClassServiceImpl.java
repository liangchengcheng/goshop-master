package com.lcc.goshop.seller.service;

import com.github.pagehelper.PageInfo;
import com.lcc.goshop.commons.utils.PageUtils;
import com.lcc.goshop.manager.i.StoreService;
import com.lcc.goshop.manager.mapper.AlbumClassMapper;
import com.lcc.goshop.manager.mapper.AlbumPicMapper;
import com.lcc.goshop.manager.pojo.AlbumClass;
import com.lcc.goshop.manager.pojo.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by lcc on 2017/2/15.
 */
@Service
public class AlbumClassServiceImpl implements AlbumClassService {
    @Autowired
    AlbumClassMapper albumClassMapper;

    @Autowired
    AlbumPicMapper albumPicMapper;

    @Autowired
    StoreService storeService;

    public PageInfo<AlbumClass> findByStoreId(Integer curPage, Integer pageSize, Long memberId, Integer sortValue) {
        PageUtils.startPage(curPage, pageSize);
        List<AlbumClass> list = albumClassMapper.findByStoreId(memberId, sortValue);
        return new PageInfo<AlbumClass>(list);
    }

    public Integer findCountByStoreId(Long userId) {
        return albumClassMapper.findCountByStoreId(userId);
    }

    public AlbumClass findOneByAclassNameAndStoreId(String aclassName, Long userId) {
        return albumClassMapper.findOneByAclassNameAndStoreId(aclassName, userId);
    }

    public int save(AlbumClass albumClass, Long userId) {
        Store store = storeService.findByMemberId(userId);
        albumClass.setStoreId(store.getStoreId());
        return albumClassMapper.insertSelective(albumClass);
    }

    public int delete(Integer aclassId, Long userId) {
        AlbumClass albumClass = albumClassMapper.findDefaultAlbumClass(userId);
        Assert.notNull(albumClass, "没有默认相册异常！");
        Integer defaultAclassId = albumClass.getAclassId();
        Assert.isTrue(!(defaultAclassId == aclassId), "默认相册不能删除！");
        //为了控制事务，就不调用AlbumPicService，直接调用AlbumPicMapper
        albumPicMapper.updateToDefaultAlbumClass(aclassId, defaultAclassId);
        return albumClassMapper.deleteByPrimaryKey(aclassId);
    }

    public AlbumClass findByAclassIdAndUserId(Integer aclassId, Long userId) {
        return albumClassMapper.findByAclassIdAndUserId(aclassId, userId);
    }

    public int update(AlbumClass albumClass, Long userId) {
        Store store = storeService.findByMemberId(userId);
        Integer storeId = store.getStoreId();
        albumClass.setStoreId(storeId);
        return albumClassMapper.updateByStoreId(albumClass);
    }
}
