package com.wang.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wang.pojo.Projecttwo;
import com.wang.pojo.query.ProjecttwoQuery;
import com.wang.service.ProjecttwoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProjecttwoController {

    @Autowired
    private ProjecttwoService projecttwoService;


    @GetMapping("/projecttwotable")
    public String projecttwotable(Model model, ProjecttwoQuery projecttwoQuery){
        PageHelper.startPage(projecttwoQuery.getPageNum(),projecttwoQuery.getPageSize());
        List<Projecttwo> projecttwo = projecttwoService.listProjecttwo();
        PageInfo<Projecttwo> pageInfo = new PageInfo<>(projecttwo);
        model.addAttribute("page",pageInfo);
        return "projecttwotable";
    }

    @PostMapping("/projecttwotable")
    public String listProjecttwoBytest(Model model,ProjecttwoQuery projecttwoQuery){
        PageInfo<Projecttwo> projecttwoPageInfo = projecttwoService.listProjecttwoBytest(projecttwoQuery);
        model.addAttribute("page",projecttwoPageInfo);
        return "projecttwotable";
    }

    @GetMapping("/deletetwo/{id}")
    public String deletetwo(@PathVariable("id") Integer id, RedirectAttributes attributes){
        boolean b = projecttwoService.deleteProjecttwoById(id);
        if(b){
            attributes.addFlashAttribute("message","删除题目成功");
            return "redirect:/admin/projecttwotable";
        }else {
            attributes.addFlashAttribute("message","删除题目失败");
            return "redirect:/admin/projecttwotable";
        }
    }

    @GetMapping("/edittwo/{id}")
    public String toEdittwo(@PathVariable Integer id,Model model){
        model.addAttribute("projecttwo",projecttwoService.queryProjecttwoById(id));
        return "projecttwotable-table";
    }

    @PostMapping("/edittwo")
    public String edittwo(Projecttwo projecttwo,RedirectAttributes attributes){
        ProjecttwoQuery projecttwoQuery = new ProjecttwoQuery();
        Integer id = projecttwo.getId();
        projecttwoQuery.setTest(projecttwo.getTest());
        PageInfo<Projecttwo> projecttwoPageInfo = projecttwoService.listProjecttwoBytest(projecttwoQuery);
        if(id != null){
            boolean b = projecttwoService.updateProjecttwo(projecttwo);
            if(b){
                attributes.addFlashAttribute("message"," 更新题目成功");
                return "redirect:/admin/projecttwotable";
            }else {
                attributes.addFlashAttribute("message","更新题目失败");
                return "redirect:/admin/projecttwotable";
            }
        }else {
            if(projecttwoPageInfo.getSize() == 0){
                boolean b = projecttwoService.addProjecttwo(projecttwo);
                if(b){
                    attributes.addFlashAttribute("message"," 新增题目成功");
                    return "redirect:/admin/projecttwotable";
                }else {
                    attributes.addFlashAttribute("message","新增题目失败");
                    return "redirect:/admin/projecttwotable";
                }
            }else {
                attributes.addFlashAttribute("message","该题目已存在");
                return "redirect:/admin/projecttwotable";
            }
        }
    }

    @GetMapping("/updatetwo")
    public String toUpdatetwo(Model model){
        Projecttwo projecttwo = new Projecttwo();
        model.addAttribute("projecttwo",projecttwo);
        return "projecttwotable-table";
    }


}

