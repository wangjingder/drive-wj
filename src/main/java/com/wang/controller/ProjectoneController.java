package com.wang.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wang.pojo.Projectone;
import com.wang.pojo.query.ProjectoneQuery;
import com.wang.service.ProjectoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProjectoneController {

    @Autowired
    private ProjectoneService projectoneService;


    @GetMapping("/projectonetable")
    public String projectonetable(Model model, ProjectoneQuery projectoneQuery){
        PageHelper.startPage(projectoneQuery.getPageNum(),projectoneQuery.getPageSize());
        List<Projectone> projectone = projectoneService.listProjectone();
        PageInfo<Projectone> pageInfo = new PageInfo<>(projectone);
        model.addAttribute("page",pageInfo);
        return "projectonetable";
    }

    @PostMapping("/projectonetable")
    public String listProjectoneBytest(Model model,ProjectoneQuery projectoneQuery){
        PageInfo<Projectone> projectonePageInfo = projectoneService.listProjectoneBytest(projectoneQuery);
        model.addAttribute("page",projectonePageInfo);
        return "projectonetable";
    }

    @GetMapping("/deleteone/{id}")
    public String deleteone(@PathVariable("id") Integer id, RedirectAttributes attributes){
        boolean b = projectoneService.deleteProjectoneById(id);
        if(b){
            attributes.addFlashAttribute("message","删除题目成功");
            return "redirect:/admin/projectonetable";
        }else {
            attributes.addFlashAttribute("message","删除题目失败");
            return "redirect:/admin/projectonetable";
        }
    }

    @GetMapping("/editone/{id}")
    public String toEditone(@PathVariable Integer id,Model model){
        model.addAttribute("projectone",projectoneService.queryProjectoneById(id));
        return "projectonetable-table";
    }

    @PostMapping("/editone")
    public String editone(Projectone projectone,RedirectAttributes attributes){
        ProjectoneQuery projectoneQuery = new ProjectoneQuery();
        Integer id = projectone.getId();
        projectoneQuery.setTest(projectone.getTest());
        PageInfo<Projectone> projectonePageInfo = projectoneService.listProjectoneBytest(projectoneQuery);
        if(id != null){
            boolean b = projectoneService.updateProjectone(projectone);
            if(b){
                attributes.addFlashAttribute("message"," 更新题目成功");
                return "redirect:/admin/projectonetable";
            }else {
                attributes.addFlashAttribute("message","更新题目失败");
                return "redirect:/admin/projectonetable";
            }
        }else {
            if(projectonePageInfo.getSize() == 0){
                boolean b = projectoneService.addProjectone(projectone);
                if(b){
                    attributes.addFlashAttribute("message"," 新增题目成功");
                    return "redirect:/admin/projectonetable";
                }else {
                    attributes.addFlashAttribute("message","新增题目失败");
                    return "redirect:/admin/projectonetable";
                }
            }else {
                attributes.addFlashAttribute("message","该题目已存在");
                return "redirect:/admin/projectonetable";
            }
        }
    }

    @GetMapping("/updateone")
    public String toUpdateone(Model model){
        Projectone projectone = new Projectone();
        model.addAttribute("projectone",projectone);
        return "projectonetable-table";
    }


}

