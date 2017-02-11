package com.lcc.goshop.manager.service;

import com.lcc.goshop.manager.i.GoodsTypeService;
import com.lcc.goshop.manager.mapper.GoodsTypeMapper;
import com.lcc.goshop.manager.pojo.GoodsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("goodsTypeService")
public class GoodsTypeServiceImpl implements GoodsTypeService {

    @Autowired
    GoodsTypeMapper goodsTypeMapper;

    public List<GoodsType> findAll() {
        return goodsTypeMapper.findAll();
    }
}
