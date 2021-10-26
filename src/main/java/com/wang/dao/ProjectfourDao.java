package com.wang.dao;

import com.wang.pojo.Projectfour;
import com.wang.pojo.query.ProjectfourQuery;

import java.util.List;

public interface ProjectfourDao {

    public List<Projectfour> listProjectfour();


    // 根据id查询题目
    public Projectfour queryProjectfourById(Integer id);

    // 根据用户名来查询题目  并分页展示
    public List<Projectfour> listProjectfourBytest(ProjectfourQuery projectfourQuery);

    //根据id删除题目
    public int deleteProjectfourById(Integer id);

    // 修改题目
    public int updateProjectfour(Projectfour projectfour);

    // 新增题目
    public int addProjectfour(Projectfour projectfour);
}
