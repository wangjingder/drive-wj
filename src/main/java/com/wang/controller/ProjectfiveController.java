package com.wang.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wang.pojo.Projectfive;
import com.wang.pojo.query.ProjectfiveQuery;
import com.wang.service.ProjectfiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProjectfiveController {

    @Autowired
    private ProjectfiveService projectfiveService;


    @GetMapping("/projectfivetable")
    public String projectfivetable(Model model, ProjectfiveQuery projectfiveQuery){
        PageHelper.startPage(projectfiveQuery.getPageNum(),projectfiveQuery.getPageSize());
        List<Projectfive> projectfive = projectfiveService.listProjectfive();
        PageInfo<Projectfive> pageInfo = new PageInfo<>(projectfive);
        model.addAttribute("page",pageInfo);
        return "projectfivetable";
    }

    @PostMapping("/projectfivetable")
    public String listProjectfiveBytest(Model model,ProjectfiveQuery projectfiveQuery){
        PageInfo<Projectfive> projectfivePageInfo = projectfiveService.listProjectfiveBytest(projectfiveQuery);
        model.addAttribute("page",projectfivePageInfo);
        return "projectfivetable";
    }

    @GetMapping("/deletefive/{id}")
    public String deletefive(@PathVariable("id") Integer id, RedirectAttributes attributes){
        boolean b = projectfiveService.deleteProjectfiveById(id);
        if(b){
            attributes.addFlashAttribute("message","删除题目成功");
            return "redirect:/admin/projectfivetable";
        }else {
            attributes.addFlashAttribute("message","删除题目失败");
            return "redirect:/admin/projectfivetable";
        }
    }

    @GetMapping("/editfive/{id}")
    public String toEditfive(@PathVariable Integer id,Model model){
        model.addAttribute("projectfive",projectfiveService.queryProjectfiveById(id));
        return "projectfivetable-table";
    }

    @PostMapping("/editfive")
    public String editfive(Projectfive projectfive,RedirectAttributes attributes){
        ProjectfiveQuery projectfiveQuery = new ProjectfiveQuery();
        Integer id = projectfive.getId();
        projectfiveQuery.setTest(projectfive.getTest());
        PageInfo<Projectfive> projectfivePageInfo = projectfiveService.listProjectfiveBytest(projectfiveQuery);
        if(id != null){
            boolean b = projectfiveService.updateProjectfive(projectfive);
            if(b){
                attributes.addFlashAttribute("message"," 更新题目成功");
                return "redirect:/admin/projectfivetable";
            }else {
                attributes.addFlashAttribute("message","更新题目失败");
                return "redirect:/admin/projectfivetable";
            }
        }else {
            if(projectfivePageInfo.getSize() == 0){
                boolean b = projectfiveService.addProjectfive(projectfive);
                if(b){
                    attributes.addFlashAttribute("message"," 新增题目成功");
                    return "redirect:/admin/projectfivetable";
                }else {
                    attributes.addFlashAttribute("message","新增题目失败");
                    return "redirect:/admin/projectfivetable";
                }
            }else {
                attributes.addFlashAttribute("message","该题目已存在");
                return "redirect:/admin/projectfivetable";
            }
        }
    }

    @GetMapping("/updatefive")
    public String toUpdatefive(Model model){
        Projectfive projectfive = new Projectfive();
        model.addAttribute("projectfive",projectfive);
        return "projectfivetable-table";
    }


}

