package com.wang.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wang.pojo.User;
import com.wang.pojo.query.UserQuery;
import com.wang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/usertable")
    public String usertable(Model model, UserQuery userQuery){
        PageHelper.startPage(userQuery.getPageNum(),userQuery.getPageSize());
        List<User> users = userService.listUser();
        PageInfo<User> pageInfo = new PageInfo<>(users);
        model.addAttribute("page",pageInfo);
        return "usertable";
    }

    @ResponseBody
    @RequestMapping("/test")
    public JSONObject test(int num){
        JSONObject json = new JSONObject();
        PageHelper.startPage(num,10);
        List<User> users = userService.listUser();
        PageInfo<User> pageInfo = new PageInfo<>(users);
        json.put("code",200);
        json.put("data",pageInfo.getList());
        return json;
    }

    @PostMapping("/usertable")
    public String listUserByuserName(Model model,UserQuery userQuery){
        PageInfo<User> userPageInfo = userService.listUserByuserName(userQuery);
        model.addAttribute("page",userPageInfo);
        return "usertable";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes attributes){
        boolean b = userService.deleteUserById(id);
        if(b){
            attributes.addFlashAttribute("message","删除用户成功");
            return "redirect:/admin/usertable";
        }else {
            attributes.addFlashAttribute("message","删除用户失败");
            return "redirect:/admin/usertable";
        }
    }

    @GetMapping("/edit/{id}")
    public String toEdit(@PathVariable Integer id,Model model){
        model.addAttribute("user",userService.queryUserById(id));
        return "usertable-table";
    }

    @PostMapping("/edit")
    public String edit(User user,RedirectAttributes attributes){
        UserQuery userQuery = new UserQuery();
        Integer id = user.getId();
        userQuery.setUserName(user.getUserName());
        PageInfo<User> userPageInfo = userService.listUserByuserName(userQuery);
        if(id != null){
                boolean b = userService.updateUser(user);
                if(b){
                    attributes.addFlashAttribute("message"," 更新用户成功");
                    return "redirect:/admin/usertable";
                }else {
                    attributes.addFlashAttribute("message","更新用户失败");
                    return "redirect:/admin/usertable";
                }
        }else {
            if(userPageInfo.getSize() == 0){
                boolean b = userService.addUser(user);
                if(b){
                    attributes.addFlashAttribute("message"," 新增用户成功");
                    return "redirect:/admin/usertable";
                }else {
                    attributes.addFlashAttribute("message","新增用户失败");
                    return "redirect:/admin/usertable";
                }
            }else {
                attributes.addFlashAttribute("message","该用户名已存在");
                return "redirect:/admin/usertable";
            }
        }
    }

    @GetMapping("/update")
    public String toUpdate(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "usertable-table";
    }


}
