package com.wang.dao;

import com.wang.pojo.Projectfive;
import com.wang.pojo.query.ProjectfiveQuery;

import java.util.List;

public interface ProjectfiveDao {

    public List<Projectfive> listProjectfive();


    // 根据id查询题目
    public Projectfive queryProjectfiveById(Integer id);

    // 根据用户名来查询题目  并分页展示
    public List<Projectfive> listProjectfiveBytest(ProjectfiveQuery projectfiveQuery);

    //根据id删除题目
    public int deleteProjectfiveById(Integer id);

    // 修改题目
    public int updateProjectfive(Projectfive projectfive);

    // 新增题目
    public int addProjectfive(Projectfive projectfive);


}
