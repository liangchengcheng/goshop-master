package com.lcc.goshop.commons.utils;

import com.github.pagehelper.PageHelper;

/**
 * Created by lcc on 2017/2/7.
 */
public class PageUtils {

    public static void startPage(Integer curPage, Integer pageSize){
        //1、设置分页
        if (curPage == null) {
            curPage = 1;
        }
        if (pageSize == null) {
            pageSize = 20;
        }
        PageHelper.startPage(curPage, pageSize);
    }
}
