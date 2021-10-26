package com.wang.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wang.pojo.Projectseven;
import com.wang.pojo.query.ProjectsevenQuery;
import com.wang.service.ProjectsevenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProjectsevenController {

    @Autowired
    private ProjectsevenService projectsevenService;


    @GetMapping("/projectseventable")
    public String projectseventable(Model model, ProjectsevenQuery projectsevenQuery){
        PageHelper.startPage(projectsevenQuery.getPageNum(),projectsevenQuery.getPageSize());
        List<Projectseven> projectseven = projectsevenService.listProjectseven();
        PageInfo<Projectseven> pageInfo = new PageInfo<>(projectseven);
        model.addAttribute("page",pageInfo);
        return "projectseventable";
    }

    @PostMapping("/projectseventable")
    public String listProjectsevenBytest(Model model,ProjectsevenQuery projectsevenQuery){
        PageInfo<Projectseven> projectsevenPageInfo = projectsevenService.listProjectsevenBytest(projectsevenQuery);
        model.addAttribute("page",projectsevenPageInfo);
        return "projectseventable";
    }

    @GetMapping("/deleteseven/{id}")
    public String deleteseven(@PathVariable("id") Integer id, RedirectAttributes attributes){
        boolean b = projectsevenService.deleteProjectsevenById(id);
        if(b){
            attributes.addFlashAttribute("message","删除题目成功");
            return "redirect:/admin/projectseventable";
        }else {
            attributes.addFlashAttribute("message","删除题目失败");
            return "redirect:/admin/projectseventable";
        }
    }

    @GetMapping("/editseven/{id}")
    public String toEditseven(@PathVariable Integer id,Model model){
        model.addAttribute("projectseven",projectsevenService.queryProjectsevenById(id));
        return "projectseventable-table";
    }

    @PostMapping("/editseven")
    public String editseven(Projectseven projectseven,RedirectAttributes attributes){
        ProjectsevenQuery projectsevenQuery = new ProjectsevenQuery();
        Integer id = projectseven.getId();
        projectsevenQuery.setTest(projectseven.getTest());
        PageInfo<Projectseven> projectsevenPageInfo = projectsevenService.listProjectsevenBytest(projectsevenQuery);
        if(id != null){
            boolean b = projectsevenService.updateProjectseven(projectseven);
            if(b){
                attributes.addFlashAttribute("message"," 更新题目成功");
                return "redirect:/admin/projectseventable";
            }else {
                attributes.addFlashAttribute("message","更新题目失败");
                return "redirect:/admin/projectseventable";
            }
        }else {
            if(projectsevenPageInfo.getSize() == 0){
                boolean b = projectsevenService.addProjectseven(projectseven);
                if(b){
                    attributes.addFlashAttribute("message"," 新增题目成功");
                    return "redirect:/admin/projectseventable";
                }else {
                    attributes.addFlashAttribute("message","新增题目失败");
                    return "redirect:/admin/projectseventable";
                }
            }else {
                attributes.addFlashAttribute("message","该题目已存在");
                return "redirect:/admin/projectseventable";
            }
        }
    }

    @GetMapping("/updateseven")
    public String toUpdateseven(Model model){
        Projectseven projectseven = new Projectseven();
        model.addAttribute("projectseven",projectseven);
        return "projectseventable-table";
    }


}

