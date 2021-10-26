package com.wang.dao;

import com.wang.pojo.Pinglun;
import com.wang.pojo.Quanzi;
import com.wang.pojo.User;
import com.wang.pojo.query.PinglunQuery;

import java.util.List;

public interface PinglunDao {

    public List<Pinglun> listPinglun();


    // 根据id查询题目
    public Pinglun queryPinglunById(Integer id);

    // 根据用户名来查询题目  并分页展示
    public List<Pinglun> listPinglunBytest(PinglunQuery pinglunQuery);

    //根据id删除题目
    public int deletePinglunById(Integer id);

    // 修改题目
    public int updatePinglun(Pinglun pinglun);

    // 新增题目
    public int addPinglun(Pinglun pinglun);

    List<Pinglun> queryPinglun(String city);

    boolean pinglunRegister(Pinglun pinglun);
}