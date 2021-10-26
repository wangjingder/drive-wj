package com.wang.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wang.pojo.Projectfour;
import com.wang.pojo.query.ProjectfourQuery;
import com.wang.service.ProjectfourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProjectfourController {

    @Autowired
    private ProjectfourService projectfourService;


    @GetMapping("/projectfourtable")
    public String projectfourtable(Model model, ProjectfourQuery projectfourQuery){
        PageHelper.startPage(projectfourQuery.getPageNum(),projectfourQuery.getPageSize());
        List<Projectfour> projectfour = projectfourService.listProjectfour();
        PageInfo<Projectfour> pageInfo = new PageInfo<>(projectfour);
        model.addAttribute("page",pageInfo);
        return "projectfourtable";
    }

    @PostMapping("/projectfourtable")
    public String listProjectfourBytest(Model model,ProjectfourQuery projectfourQuery){
        PageInfo<Projectfour> projectfourPageInfo = projectfourService.listProjectfourBytest(projectfourQuery);
        model.addAttribute("page",projectfourPageInfo);
        return "projectfourtable";
    }

    @GetMapping("/deletefour/{id}")
    public String deletefour(@PathVariable("id") Integer id, RedirectAttributes attributes){
        boolean b = projectfourService.deleteProjectfourById(id);
        if(b){
            attributes.addFlashAttribute("message","删除题目成功");
            return "redirect:/admin/projectfourtable";
        }else {
            attributes.addFlashAttribute("message","删除题目失败");
            return "redirect:/admin/projectfourtable";
        }
    }

    @GetMapping("/editfour/{id}")
    public String toEditfour(@PathVariable Integer id,Model model){
        model.addAttribute("projectfour",projectfourService.queryProjectfourById(id));
        return "projectfourtable-table";
    }

    @PostMapping("/editfour")
    public String editfour(Projectfour projectfour,RedirectAttributes attributes){
        ProjectfourQuery projectfourQuery = new ProjectfourQuery();
        Integer id = projectfour.getId();
        projectfourQuery.setTest(projectfour.getTest());
        PageInfo<Projectfour> projectfourPageInfo = projectfourService.listProjectfourBytest(projectfourQuery);
        if(id != null){
            boolean b = projectfourService.updateProjectfour(projectfour);
            if(b){
                attributes.addFlashAttribute("message"," 更新题目成功");
                return "redirect:/admin/projectfourtable";
            }else {
                attributes.addFlashAttribute("message","更新题目失败");
                return "redirect:/admin/projectfourtable";
            }
        }else {
            if(projectfourPageInfo.getSize() == 0){
                boolean b = projectfourService.addProjectfour(projectfour);
                if(b){
                    attributes.addFlashAttribute("message"," 新增题目成功");
                    return "redirect:/admin/projectfourtable";
                }else {
                    attributes.addFlashAttribute("message","新增题目失败");
                    return "redirect:/admin/projectfourtable";
                }
            }else {
                attributes.addFlashAttribute("message","该题目已存在");
                return "redirect:/admin/projectfourtable";
            }
        }
    }

    @GetMapping("/updatefour")
    public String toUpdatefour(Model model){
        Projectfour projectfour = new Projectfour();
        model.addAttribute("projectfour",projectfour);
        return "projectfourtable-table";
    }


}

