package com.lcc.goshop.manager.service;

import com.lcc.goshop.manager.i.AlbumPicService;
import com.lcc.goshop.manager.i.StoreService;
import com.lcc.goshop.manager.mapper.AlbumPicMapper;
import com.lcc.goshop.manager.pojo.AlbumPic;
import com.lcc.goshop.manager.pojo.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("albumPicService")
public class AlbumPicServiceImpl implements AlbumPicService {


    @Autowired
    StoreService storeService;

    @Autowired
    AlbumPicMapper albumPicMapper;

    public AlbumPic save(Long userId,Integer aclassId, String imagePath, Long size, String name, String originalFilename,String apicSpec) {
        Store store=storeService.findByMemberId(userId);
        AlbumPic albumPic = new AlbumPic();
        albumPic.setAclassId(aclassId);
        albumPic.setStoreId(store.getStoreId());
        albumPic.setApicCover(imagePath);
        albumPic.setApicName(name);
        albumPic.setApicTag(originalFilename);
        albumPic.setApicSpec(apicSpec);
        albumPic.setApicSize(size.intValue());
        albumPicMapper.insertSelective(albumPic);
        return albumPic;
    }
}
