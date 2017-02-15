package com.lcc.goshop.seller.service;

import com.lcc.goshop.seller.mapper.GoodsCommonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lcc on 2017/2/15.
 */
@Service
public class GoodsCommonServiceImpl implements GoodsCommonService {

    @Autowired
    GoodsCommonMapper goodsCommonMapper;
}
