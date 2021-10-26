package com.wang.dao;

import com.wang.pojo.Projectsix;
import com.wang.pojo.query.ProjectsixQuery;

import java.util.List;

public interface ProjectsixDao {

    public List<Projectsix> listProjectsix();


    // 根据id查询题目
    public Projectsix queryProjectsixById(Integer id);

    // 根据用户名来查询题目  并分页展示
    public List<Projectsix> listProjectsixBytest(ProjectsixQuery projectsixQuery);

    //根据id删除题目
    public int deleteProjectsixById(Integer id);

    // 修改题目
    public int updateProjectsix(Projectsix projectsix);

    // 新增题目
    public int addProjectsix(Projectsix projectsix);


}
