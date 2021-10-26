package com.wang.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wang.pojo.Projectthree;
import com.wang.pojo.query.ProjectthreeQuery;
import com.wang.service.ProjectthreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProjectthreeController {

    @Autowired
    private ProjectthreeService projectthreeService;


    @GetMapping("/projectthreetable")
    public String projectthreetable(Model model, ProjectthreeQuery projectthreeQuery){
        PageHelper.startPage(projectthreeQuery.getPageNum(),projectthreeQuery.getPageSize());
        List<Projectthree> projectthree = projectthreeService.listProjectthree();
        PageInfo<Projectthree> pageInfo = new PageInfo<>(projectthree);
        model.addAttribute("page",pageInfo);
        return "projectthreetable";
    }

    @PostMapping("/projectthreetable")
    public String listProjectthreeBytest(Model model,ProjectthreeQuery projectthreeQuery){
        PageInfo<Projectthree> projectthreePageInfo = projectthreeService.listProjectthreeBytest(projectthreeQuery);
        model.addAttribute("page",projectthreePageInfo);
        return "projectthreetable";
    }

    @GetMapping("/deletethree/{id}")
    public String deletethree(@PathVariable("id") Integer id, RedirectAttributes attributes){
        boolean b = projectthreeService.deleteProjectthreeById(id);
        if(b){
            attributes.addFlashAttribute("message","删除题目成功");
            return "redirect:/admin/projectthreetable";
        }else {
            attributes.addFlashAttribute("message","删除题目失败");
            return "redirect:/admin/projectthreetable";
        }
    }

    @GetMapping("/editthree/{id}")
    public String toEditthree(@PathVariable Integer id,Model model){
        model.addAttribute("projectthree",projectthreeService.queryProjectthreeById(id));
        return "projectthreetable-table";
    }

    @PostMapping("/editthree")
    public String editthree(Projectthree projectthree,RedirectAttributes attributes){
        ProjectthreeQuery projectthreeQuery = new ProjectthreeQuery();
        Integer id = projectthree.getId();
        projectthreeQuery.setTest(projectthree.getTest());
        PageInfo<Projectthree> projectthreePageInfo = projectthreeService.listProjectthreeBytest(projectthreeQuery);
        if(id != null){
            boolean b = projectthreeService.updateProjectthree(projectthree);
            if(b){
                attributes.addFlashAttribute("message"," 更新题目成功");
                return "redirect:/admin/projectthreetable";
            }else {
                attributes.addFlashAttribute("message","更新题目失败");
                return "redirect:/admin/projectthreetable";
            }
        }else {
            if(projectthreePageInfo.getSize() == 0){
                boolean b = projectthreeService.addProjectthree(projectthree);
                if(b){
                    attributes.addFlashAttribute("message"," 新增题目成功");
                    return "redirect:/admin/projectthreetable";
                }else {
                    attributes.addFlashAttribute("message","新增题目失败");
                    return "redirect:/admin/projectthreetable";
                }
            }else {
                attributes.addFlashAttribute("message","该题目已存在");
                return "redirect:/admin/projectthreetable";
            }
        }
    }

    @GetMapping("/updatethree")
    public String toUpdatethree(Model model){
        Projectthree projectthree = new Projectthree();
        model.addAttribute("projectthree",projectthree);
        return "projectthreetable-table";
    }


}

