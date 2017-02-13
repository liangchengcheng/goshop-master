package com.lcc.goshop.manager.controller;

import com.github.pagehelper.PageInfo;
import com.lcc.goshop.commons.pojo.ResponseStatus;
import com.lcc.goshop.manager.i.StoreClassService;
import com.lcc.goshop.manager.pojo.StoreClass;
import com.lcc.goshop.manager.utils.Jump;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by lcc on 2017/2/13.
 */

@Controller
@RequestMapping("/store_class")
public class StoreClassController {

    @Autowired
    StoreClassService storeClassService;

    @RequestMapping(value = "/store_class", method = RequestMethod.GET)
    public String index(@RequestParam(value = "p", required = false) Integer curPage,
                        Model model, HttpServletRequest request) {
        PageInfo<StoreClass> page = storeClassService.findGradeByParentId(curPage, 20, null);
        model.addAttribute("P_PAGE", page);
        return "store/store_class";
    }

    @RequestMapping(value = "/store_class", method = RequestMethod.POST)
    public String delete(Long[] check_gc_id, Model model, HttpServletRequest request) {
        String url = request.getContextPath();
        storeClassService.delete(check_gc_id);
        return Jump.get(url + "/store_class/store_class", "删除成功！");
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String savePage(Long sc_parent_id, Model model, HttpServletRequest request) {
        List<StoreClass> storeClassList = storeClassService.findByParentId(null);
        model.addAttribute("P_STORE_CLASS", storeClassList);
        model.addAttribute("P_PARENT_ID", sc_parent_id);
        return "store/store_class_add";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editPage(@RequestParam(value = "sc_id", required = false) Long id,
                           Model model, HttpServletRequest request) {
        StoreClass storeClass = storeClassService.findOne(id);
        Assert.notNull(storeClass, "没有此id可编辑！");
        model.addAttribute("P_STORE_CLASS", storeClass);
        return "store/store_class_edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(StoreClass storeClass, HttpServletRequest request) {
        String url = request.getRequestURI();
        storeClassService.save(storeClass);
        return Jump.get(url, "保存成功！");
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(StoreClass storeClass, HttpServletRequest request) {
        String url = request.getContextPath();
        storeClassService.update(storeClass);
        return Jump.get(url + "/store_class/store_class", "修改成功！");
    }

    @RequestMapping("/delete")
    public String delete(Long sc_id, HttpServletRequest request) {
        String url = request.getContextPath();
        storeClassService.delete(sc_id);
        return Jump.get(url + "/store_class/store_class", "删除成功！");
    }

    @RequestMapping("check_class_name")
    @ResponseBody
    public String checkClassName(String name, Long parentId, Long id, HttpServletRequest request) {
        try {
            boolean is = storeClassService.checkByIdNameParentId(id, name, parentId);
            return ResponseStatus.get(is);
        } catch (Exception e) {
            return ResponseStatus.get(false);
        }
    }

    @RequestMapping("inline_edit")
    @ResponseBody
    public String inlineEdit(String column, String value, Long id, HttpServletRequest request) {
        try {
            if (column.equals("sc_sort")) {
                storeClassService.updateSort(id, value);
            } else if (column.equals("sc_name")) {
                storeClassService.updateName(id, value);
            }
            return ResponseStatus.get(true);
        } catch (Exception e) {
            return ResponseStatus.get(false);
        }
    }

    @RequestMapping("children")
    @ResponseBody
    public Object children(Long sc_parent_id, HttpServletRequest request) {
        return storeClassService.findByParentId(sc_parent_id);
    }
}
