package com.lcc.goshop.manager.controller;

import com.lcc.goshop.common.attachment.AttachmentService;
import com.lcc.goshop.manager.i.GoodsClassService;
import com.lcc.goshop.manager.i.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lcc on 2017/2/12.
 */
@Controller
@RequestMapping("/goods_class")
public class GoodsClassController {
    @Autowired
    GoodsTypeService goodsTypeService;

    @Autowired
    GoodsClassService goodsClassService;

    @Autowired
    AttachmentService attachmentService;

    
}
