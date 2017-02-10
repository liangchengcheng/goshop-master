package com.lcc.goshop.manager.i;

import com.github.pagehelper.PageInfo;
import com.lcc.goshop.manager.pojo.StoreJoin;

/**
 * Created by lcc on 2017/2/10.
 */
public interface StoreJoinManageService {
    PageInfo<StoreJoin> findAll(Integer curPage, Integer pageSize);

    StoreJoin find(Long memberId);

    void saveVerify(Long member_id, String verify_type, String join_message, String[] commis_rate);
}
