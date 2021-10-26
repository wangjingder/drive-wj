package com.wang.dao;

import com.wang.pojo.Projectseven;
import com.wang.pojo.User;
import com.wang.pojo.query.ProjectsevenQuery;

import java.util.List;

public interface ProjectsevenDao {

    public List<Projectseven> listProjectseven();


    // 根据id查询题目
    public Projectseven queryProjectsevenById(Integer id);

    // 根据用户名来查询题目  并分页展示
    public List<Projectseven> listProjectsevenBytest(ProjectsevenQuery projectsevenQuery);

    //根据id删除题目
    public int deleteProjectsevenById(Integer id);

    // 修改题目
    public int updateProjectseven(Projectseven projectseven);

    // 新增题目
    public int addProjectseven(Projectseven projectseven);

    List<Projectseven> queryProjectseven(String city);
}