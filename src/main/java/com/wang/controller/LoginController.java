package com.wang.controller;


import com.wang.pojo.User;
import com.wang.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/admin")
public class LoginController {

    @Resource
    private UserService userService;


    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }


    @RequestMapping("/to_login")
    public String to_login(@RequestParam("userName") String userName, @RequestParam("password") String password, Model model, HttpServletRequest request){
        User userAdmin = userService.queryAdmin(userName,password);
        if (userAdmin == null){
            model.addAttribute("msg","当前账户或密码错误！！！");
            return "login";
        }else {
            request.getSession().setAttribute("userName",userName);
            return "redirect:/admin/index";
        }
    }
}
