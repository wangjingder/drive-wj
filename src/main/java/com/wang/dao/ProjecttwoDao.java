package com.wang.dao;

import com.wang.pojo.Projecttwo;
import com.wang.pojo.query.ProjecttwoQuery;

import java.util.List;

public interface ProjecttwoDao {

    public List<Projecttwo> listProjecttwo();


    // 根据id查询题目
    public Projecttwo queryProjecttwoById(Integer id);

    // 根据用户名来查询题目  并分页展示
    public List<Projecttwo> listProjecttwoBytest(ProjecttwoQuery projecttwoQuery);

    //根据id删除题目
    public int deleteProjecttwoById(Integer id);

    // 修改题目
    public int updateProjecttwo(Projecttwo projecttwo);

    // 新增题目
    public int addProjecttwo(Projecttwo projecttwo);
}
