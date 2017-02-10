package com.lcc.goshop.manager.i;

import com.lcc.goshop.manager.pojo.AlbumPic;

/**
 * Created by lcc on 2017/2/10.
 */
public interface AlbumPicService {
    AlbumPic save(Long userId, Integer aclassId, String imagePath, Long size, String name, String originalFilename, String apicSpec);

}
