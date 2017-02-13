package com.lcc.goshop.manager.controller;

import com.github.pagehelper.PageInfo;
import com.lcc.goshop.commons.context.CustomTimestampEditor;
import com.lcc.goshop.commons.utils.JsonUtils;
import com.lcc.goshop.manager.i.StoreClassService;
import com.lcc.goshop.manager.i.StoreGradeService;
import com.lcc.goshop.manager.i.StoreJoinManageService;
import com.lcc.goshop.manager.i.StoreService;
import com.lcc.goshop.manager.model.JsonManagement;
import com.lcc.goshop.manager.pojo.*;
import com.lcc.goshop.manager.utils.Jump;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lcc on 2017/2/13.
 */

@Controller
@RequestMapping("/store")
public class StoreController {

    @Autowired
    StoreService storeService;

    @Autowired
    StoreGradeService storeGradeService;

    @Autowired
    StoreClassService storeClassService;

    @Autowired
    StoreJoinManageService storeJoinManageService;

    @InitBinder
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) throws Exception {
        //对于需要转换为Date类型的属性，使用DateEditor进行处理
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        SimpleDateFormat datetimeFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        datetimeFormat.setLenient(false);

        binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(
                dateFormat, true));
        binder.registerCustomEditor(java.sql.Timestamp.class,
                new CustomTimestampEditor(datetimeFormat, true));
    }

    @RequestMapping(value = "/store", method = RequestMethod.GET)
    public String store(@RequestParam(value = "p", required = false) Integer curPage,
                        Integer grade_id,//等级
                        String owner_and_name,//店主
                        String store_type,//店铺类型
                        String store_name,//店铺名称
                        Model model, HttpServletRequest request) {
        PageInfo<Store> page = storeService.find(grade_id, owner_and_name, store_type, store_name, curPage, 20);
        model.addAttribute("P_PAGE", page);

        List<StoreGrade> storeGradeList = storeGradeService.findAll();
        Map<String, String> gradeMap = new HashMap<String, String>();
        for (StoreGrade o : storeGradeList) {
            gradeMap.put(String.valueOf(o.getSgId()), o.getSgName());
        }
        model.addAttribute("P_STORE_GRADE_MAP", gradeMap);
        return "store/store";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Integer storeId, Model model, HttpServletRequest request) {
        Store store = storeService.findOne(storeId);
        model.addAttribute("P_STORE", store);
        List<StoreGrade> storeGradeList = storeGradeService.findAll();
        model.addAttribute("P_STORE_GRADE", storeGradeList);
        //店铺分类
        List<StoreClass> storeClassParentList = storeClassService.findTreeByParentId(null);
        model.addAttribute("P_PARENT_STORECLASS", storeClassParentList);
        return "store/store_edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String save(StoreWithBLOBs store, Model model, HttpServletRequest request) {
        String url = request.getContextPath();
        storeService.update(store);
        return Jump.get(url + "/store/store", "修改成功！");
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String show(Integer storeId, Model model, HttpServletRequest request) {
        Store store = storeService.findOne(storeId);
        StoreJoin storeJoin = storeJoinManageService.find(store.getMemberId());
        model.addAttribute("P_STORE_JOIN", storeJoin);

        String scIds = storeJoin.getStoreClassIds();
        List<JsonManagement> jsonManagementList = JsonUtils.jsonToList(scIds, JsonManagement.class);
        model.addAttribute("P_CLASS_LIST", jsonManagementList);
        return "store/store_show";
    }

}