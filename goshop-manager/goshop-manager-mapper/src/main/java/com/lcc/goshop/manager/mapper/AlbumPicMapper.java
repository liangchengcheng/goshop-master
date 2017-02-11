package com.lcc.goshop.manager.mapper;

import com.lcc.goshop.manager.pojo.AlbumPic;
import org.apache.ibatis.annotations.Param;

/**
 * Created by lcc on 2017/2/10.
 */
public interface AlbumPicMapper {
    int deleteByPrimaryKey(Integer apicId);

    int insert(AlbumPic record);

    int insertSelective(AlbumPic record);

    AlbumPic selectByPrimaryKey(Integer apicId);

    int updateByPrimaryKeySelective(AlbumPic record);

    int updateByPrimaryKey(AlbumPic record);

    /**
     * 更新用户当前相册下所有图片到默认相册
     * @param aclassId 来源相册
     * @param toAclassId 到默认相册
     */
    int updateToDefaultAlbumClass(@Param("aclassId")Integer aclassId, @Param("toAclassId")Integer toAclassId);
}
