package com.wang.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wang.dao.UserDao;
import com.wang.pojo.User;
import com.wang.pojo.query.UserQuery;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service   //  交由spring容齐管理
public class UserServiceImpl implements UserService{
    @Resource
    private UserDao userDao;

    @Override
    public List<User> listUser() {
        return userDao.listUser();
    }

    @Override
    public PageInfo<User> listUserByuserName(UserQuery userQuery) {
        PageHelper.startPage(userQuery.getPageNum(),userQuery.getPageSize());
        return new PageInfo<User>(userDao.listUserByuserName(userQuery));
    }

    @Override
    public boolean deleteUserById(Integer id) {
        int i = userDao.deleteUserById(id);
        if(i > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public User queryUserById(Integer id) {
        return userDao.queryUserById(id);
    }

    @Override
    public boolean updateUser(User user) {
        int i = userDao.updateUser(user);
        if(i > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean addUser(User user) {
        return userDao.addUser(user) > 0 ? true : false ;
    }

    @Override
    public User queryAdmin(String userName, String password) {
        return userDao.queryAdmin(userName,password);
    }

    @Override
    public User queryUser(String userName, String password) {
        return userDao.queryUser(userName,password);
    }

    @Override
    public boolean userRegister(User user) {
        return userDao.userRegister(user);
    }

    @Override
    public boolean loginuserName(String userName) {
        User user = userDao.loginuserName(userName);
        if (user == null){
            return false;
        }else {
            return true;
        }
    }


}