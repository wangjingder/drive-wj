package com.wang.dao;

import com.wang.pojo.Projectone;
import com.wang.pojo.query.ProjectoneQuery;

import java.util.List;

public interface ProjectoneDao {

    public List<Projectone> listProjectone();


    // 根据id查询题目
    public Projectone queryProjectoneById(Integer id);

    // 根据用户名来查询题目  并分页展示
    public List<Projectone> listProjectoneBytest(ProjectoneQuery projectoneQuery);

    //根据id删除题目
    public int deleteProjectoneById(Integer id);

    // 修改题目
    public int updateProjectone(Projectone projectone);

    // 新增题目
    public int addProjectone(Projectone projectone);


}
