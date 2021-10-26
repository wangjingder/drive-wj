package com.wang.service;

import com.github.pagehelper.PageInfo;
import com.wang.pojo.User;
import com.wang.pojo.query.UserQuery;


import java.util.List;
import java.util.Map;

public interface UserService {

    public List<User> listUser();

    public PageInfo<User> listUserByuserName(UserQuery userQuery);

    public boolean deleteUserById(Integer id);

    public User queryUserById(Integer id);

    public boolean updateUser(User user);

    public boolean addUser(User user);

    User queryAdmin(String userName, String password);

    User queryUser(String userName, String password);

    boolean userRegister(User user);

    boolean loginuserName(String userName);
}
