package com.wang.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wang.pojo.Projectsix;
import com.wang.pojo.query.ProjectsixQuery;
import com.wang.service.ProjectsixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProjectsixController {

    @Autowired
    private ProjectsixService projectsixService;


    @GetMapping("/projectsixtable")
    public String projectsixtable(Model model, ProjectsixQuery projectsixQuery){
        PageHelper.startPage(projectsixQuery.getPageNum(),projectsixQuery.getPageSize());
        List<Projectsix> projectsix = projectsixService.listProjectsix();
        PageInfo<Projectsix> pageInfo = new PageInfo<>(projectsix);
        model.addAttribute("page",pageInfo);
        return "projectsixtable";
    }

    @PostMapping("/projectsixtable")
    public String listProjectsixBytest(Model model,ProjectsixQuery projectsixQuery){
        PageInfo<Projectsix> projectsixPageInfo = projectsixService.listProjectsixBytest(projectsixQuery);
        model.addAttribute("page",projectsixPageInfo);
        return "projectsixtable";
    }

    @GetMapping("/deletesix/{id}")
    public String deletesix(@PathVariable("id") Integer id, RedirectAttributes attributes){
        boolean b = projectsixService.deleteProjectsixById(id);
        if(b){
            attributes.addFlashAttribute("message","删除题目成功");
            return "redirect:/admin/projectsixtable";
        }else {
            attributes.addFlashAttribute("message","删除题目失败");
            return "redirect:/admin/projectsixtable";
        }
    }

    @GetMapping("/editsix/{id}")
    public String toEditsix(@PathVariable Integer id,Model model){
        model.addAttribute("projectsix",projectsixService.queryProjectsixById(id));
        return "projectsixtable-table";
    }

    @PostMapping("/editsix")
    public String editsix(Projectsix projectsix,RedirectAttributes attributes){
        ProjectsixQuery projectsixQuery = new ProjectsixQuery();
        Integer id = projectsix.getId();
        projectsixQuery.setTest(projectsix.getTest());
        PageInfo<Projectsix> projectsixPageInfo = projectsixService.listProjectsixBytest(projectsixQuery);
        if(id != null){
            boolean b = projectsixService.updateProjectsix(projectsix);
            if(b){
                attributes.addFlashAttribute("message"," 更新题目成功");
                return "redirect:/admin/projectsixtable";
            }else {
                attributes.addFlashAttribute("message","更新题目失败");
                return "redirect:/admin/projectsixtable";
            }
        }else {
            if(projectsixPageInfo.getSize() == 0){
                boolean b = projectsixService.addProjectsix(projectsix);
                if(b){
                    attributes.addFlashAttribute("message"," 新增题目成功");
                    return "redirect:/admin/projectsixtable";
                }else {
                    attributes.addFlashAttribute("message","新增题目失败");
                    return "redirect:/admin/projectsixtable";
                }
            }else {
                attributes.addFlashAttribute("message","该题目已存在");
                return "redirect:/admin/projectsixtable";
            }
        }
    }

    @GetMapping("/updatesix")
    public String toUpdatesix(Model model){
        Projectsix projectsix = new Projectsix();
        model.addAttribute("projectsix",projectsix);
        return "projectsixtable-table";
    }


}

