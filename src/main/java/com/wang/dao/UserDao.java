package com.wang.dao;


import com.wang.pojo.User;
import com.wang.pojo.query.UserQuery;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserDao {
    // 查询所有用户
    public List<User> listUser();


    // 根据id查询用户
    public User queryUserById(Integer id);

    // 根据用户名来查询用户  并分页展示
    public List<User> listUserByuserName(UserQuery userQuery);

    //根据id删除用户
    public int deleteUserById(Integer id);

    // 修改用户
    public int updateUser(User user);

    // 新增用户
    public int addUser(User user);

    User queryAdmin(String userName, String password);

    User queryUser(String userName, String password);
    //注册时查询是否有重复的用户名
    User loginuserName(@RequestParam("userName") String userName);

    boolean userRegister(User user);
}
