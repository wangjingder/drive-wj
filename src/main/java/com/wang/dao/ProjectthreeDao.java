package com.wang.dao;

import com.wang.pojo.Projectthree;
import com.wang.pojo.query.ProjectthreeQuery;

import java.util.List;

public interface ProjectthreeDao {

    public List<Projectthree> listProjectthree();


    // 根据id查询题目
    public Projectthree queryProjectthreeById(Integer id);

    // 根据用户名来查询题目  并分页展示
    public List<Projectthree> listProjectthreeBytest(ProjectthreeQuery projectthreeQuery);

    //根据id删除题目
    public int deleteProjectthreeById(Integer id);

    // 修改题目
    public int updateProjectthree(Projectthree projectthree);

    // 新增题目
    public int addProjectthree(Projectthree projectthree);


}
