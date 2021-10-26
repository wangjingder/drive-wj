package com.wang.dao;

import com.wang.pojo.Quanzi;
import com.wang.pojo.User;
import com.wang.pojo.query.QuanziQuery;

import java.util.List;

public interface QuanziDao {

    public List<Quanzi> listQuanzi();


    // 根据id查询题目
    public Quanzi queryQuanziById(Integer id);

    // 根据用户名来查询题目  并分页展示
    public List<Quanzi> listQuanziBytest(QuanziQuery quanziQuery);

    //根据id删除题目
    public int deleteQuanziById(Integer id);

    // 修改题目
    public int updateQuanzi(Quanzi quanzi);

    // 新增题目
    public int addQuanzi(Quanzi quanzi);

    boolean quanziRegister(Quanzi quanzi);

}
