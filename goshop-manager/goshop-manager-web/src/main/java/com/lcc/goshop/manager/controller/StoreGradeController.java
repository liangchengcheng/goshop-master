package com.lcc.goshop.manager.controller;

import com.github.pagehelper.PageInfo;
import com.lcc.goshop.commons.pojo.ResponseStatus;
import com.lcc.goshop.manager.i.StoreGradeService;
import com.lcc.goshop.manager.pojo.StoreGrade;
import com.lcc.goshop.manager.utils.Jump;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by lcc on 2017/2/13.
 */
@Controller
@RequestMapping("/store_grade")
public class StoreGradeController {

    @Autowired
    StoreGradeService storeGradeService;

    @RequestMapping(value = "/store_grade", method = RequestMethod.GET)
    public String index(@RequestParam(value = "p", required = false) Integer curPage,
                        Model model, HttpServletRequest request) {
        PageInfo<StoreGrade> page = storeGradeService.findAll(curPage, 20);
        model.addAttribute("P_PAGE", page);
        return "store/store_grade";
    }

    @RequestMapping(value = "/query_name", method = RequestMethod.POST)
    public String queryName(String like_sg_name,
                            @RequestParam(value = "p", required = false) Integer curPage,
                            Model model, HttpServletRequest request) {
        PageInfo<StoreGrade> page = storeGradeService.findBySgName(like_sg_name, curPage, 20);
        model.addAttribute("P_PAGE", page);
        return "store/store_grade";
    }

    @RequestMapping(value = "/store_grade", method = RequestMethod.POST)
    public String deletes(Integer[] check_sg_id,
                          Model model, HttpServletRequest request) {
        String url = request.getContextPath();
        storeGradeService.delete(check_sg_id);
        return Jump.get(url + "/store_grade/store_grade", "删除成功！");
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String savePage(Integer id, Model model, HttpServletRequest request) {
        if (id != null) {
            StoreGrade storeGrade = storeGradeService.findOne(id);
            model.addAttribute("P_STORE_GRADE", storeGrade);
        } else {
            model.addAttribute("P_STORE_GRADE", new StoreGrade());
        }
        return "store/store_grade_save";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(StoreGrade storeGrade, Model model, HttpServletRequest request) {
        String url = request.getRequestURI();
        storeGradeService.save(storeGrade);
        return Jump.get(url, "保存成功！");
    }

    @RequestMapping("delete")
    public String delete(Integer id, Model model, HttpServletRequest request) {
        String url = request.getContextPath();
        storeGradeService.delete(id);
        return Jump.get(url + "/store_grade/store_grade", "删除成功！");
    }

    @RequestMapping("check_name")
    @ResponseBody
    public String checkName(String sgName, Integer sgId, HttpServletRequest request) {
        try {
            boolean is = storeGradeService.checkBySgIdSgName(sgName, sgId);
            return ResponseStatus.get(is);
        } catch (Exception e) {
            return ResponseStatus.get(false);
        }
    }

    @RequestMapping("check_sort")
    @ResponseBody
    public String checkSort(Integer sgSort, Integer sgId, HttpServletRequest request) {
        try {
            boolean is = storeGradeService.checkBySgIdSgSort(sgSort, sgId);
            return ResponseStatus.get(is);
        } catch (Exception e) {
            return ResponseStatus.get(false);
        }
    }
}
