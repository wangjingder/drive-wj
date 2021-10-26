package com.wang.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wang.pojo.*;
import com.wang.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping("/user")
public class UniApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectoneService projectoneService;

    @Autowired
    private ProjecttwoService projecttwoService;

    @Autowired
    private ProjectthreeService projectthreeService;

    @Autowired
    private ProjectfourService projectfourService;

    @Autowired
    private ProjectfiveService projectfiveService;

    @Autowired
    private ProjectsixService projectsixService;

    @Autowired
    private ProjectsevenService projectsevenService;

    @Autowired
    private QuanziService quanziService;

    @Autowired
    private PinglunService pinglunService;

    @ResponseBody
    @RequestMapping("/loginapp")
    public JSONObject login(@RequestParam("userName") String userName,@RequestParam("password") String password){
        JSONObject json = new JSONObject();
        User user = userService.queryUser(userName,password);
        if (user == null){
            json.put("code",400);
            json.put("msg","用户名或密码错误");
        }else {
            json.put("code",200);
            json.put("user",user);
        }
        return json;
    }

    @ResponseBody
    @RequestMapping("/city")
    public JSONObject city(@RequestParam("city") String city){
        JSONObject json = new JSONObject();
        List<Projectseven> projectseven = projectsevenService.queryProjectseven(city);
        json.put("code",200);
        json.put("projectseven",projectseven);
        return json;
    }

    @ResponseBody
    @RequestMapping("/pinglun")
    public JSONObject pinglun(@RequestParam("userId") String userId){
        JSONObject json = new JSONObject();
        List<Pinglun> pinglun = pinglunService.queryPinglun(userId);
        json.put("code",200);
        json.put("pinglun",pinglun);
        return json;
    }

    @ResponseBody
    @RequestMapping("/register")
    public JSONObject register(@RequestParam("userName") String userName, @RequestParam("password") String password, @RequestParam("phone") String phone, @RequestParam("name") String name)
    {
        JSONObject json = new JSONObject();
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setPhone(phone);
        user.setUserName(userName);
        boolean rs = userService.loginuserName(userName);
        if(!rs){
            userService.userRegister(user);
            json.put("code",200);
        }
        else{
            json.put("code",400);
        }
        return json;
    }

    @ResponseBody
    @RequestMapping("/fabu")
    public JSONObject fabu(@RequestParam("text") String text, @RequestParam("imglist") String imglist,@RequestParam("userInfo") Integer userInfo,@RequestParam("userName") String userName)
    {
        System.out.println("6666666666666"+text);
        JSONObject json = new JSONObject();
        Quanzi quanzi = new Quanzi();
        quanzi.setText(text);
        quanzi.setImglist(imglist);
        quanzi.setUserInfo(userInfo);
        quanzi.setUserName(userName);
        quanziService.quanziRegister(quanzi);
        json.put("code",200);
        return json;
    }

    @ResponseBody
    @RequestMapping("/talk")
    public JSONObject talk(@RequestParam("text") String text, @RequestParam("userId") Integer userId,@RequestParam("userName") String userName)
    {
        JSONObject json = new JSONObject();
        Pinglun pinglun = new Pinglun();
        pinglun.setText(text);
        pinglun.setUserId(userId);
        pinglun.setUserName(userName);
        pinglunService.pinglunRegister(pinglun);
        json.put("code",200);
        return json;
    }

    @ResponseBody
    @RequestMapping("/pengyouquan")
    public JSONObject pengyouquan(){
        JSONObject json = new JSONObject();
        List<Quanzi> quanzis = quanziService.listQuanzi();
        PageInfo<Quanzi> pageInfo = new PageInfo<>(quanzis);
        json.put("code",200);
        json.put("data",pageInfo.getList());
        return json;
    }

    @ResponseBody
    @RequestMapping("/testone")
    public JSONObject testone(){
        JSONObject json = new JSONObject();
        List<Projectone> projectones = projectoneService.listProjectone();
        PageInfo<Projectone> pageInfo = new PageInfo<>(projectones);
        json.put("code",200);
        json.put("data",pageInfo.getList());
        return json;
    }

    @ResponseBody
    @RequestMapping("/testtwo")
    public JSONObject testtwo(){
        JSONObject json = new JSONObject();
        List<Projecttwo> projecttwos = projecttwoService.listProjecttwo();
        PageInfo<Projecttwo> pageInfo = new PageInfo<>(projecttwos);
        json.put("code",200);
        json.put("data",pageInfo.getList());
        return json;
    }

    @ResponseBody
    @RequestMapping("/testthree")
    public JSONObject testthree(){
        JSONObject json = new JSONObject();
        List<Projectthree> projectthrees = projectthreeService.listProjectthree();
        PageInfo<Projectthree> pageInfo = new PageInfo<>(projectthrees);
        json.put("code",200);
        json.put("data",pageInfo.getList());
        return json;
    }

    @ResponseBody
    @RequestMapping("/testfour")
    public JSONObject testfour(){
        JSONObject json = new JSONObject();
        List<Projectfour> projectfours = projectfourService.listProjectfour();
        PageInfo<Projectfour> pageInfo = new PageInfo<>(projectfours);
        json.put("code",200);
        json.put("data",pageInfo.getList());
        return json;
    }

    @ResponseBody
    @RequestMapping("/testfive")
    public JSONObject testfive(){
        JSONObject json = new JSONObject();
        List<Projectfive> projectfives = projectfiveService.listProjectfive();
        PageInfo<Projectfive> pageInfo = new PageInfo<>(projectfives);
        json.put("code",200);
        json.put("data",pageInfo.getList());
        return json;
    }

    @ResponseBody
    @RequestMapping("/testsix")
    public JSONObject testsix(){
        JSONObject json = new JSONObject();
        List<Projectsix> projectsixs = projectsixService.listProjectsix();
        PageInfo<Projectsix> pageInfo = new PageInfo<>(projectsixs);
        json.put("code",200);
        json.put("data",pageInfo.getList());
        return json;
    }
}