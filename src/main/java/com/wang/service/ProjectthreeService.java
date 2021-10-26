package com.wang.service;

import com.github.pagehelper.PageInfo;
import com.wang.pojo.Projectthree;
import com.wang.pojo.query.ProjectthreeQuery;

import java.util.List;

public interface ProjectthreeService {

    public List<Projectthree> listProjectthree();

    public PageInfo<Projectthree> listProjectthreeBytest(ProjectthreeQuery projectthreeQuery);

    public boolean deleteProjectthreeById(Integer id);

    public Projectthree queryProjectthreeById(Integer id);

    public boolean updateProjectthree(Projectthree projectthree);

    public boolean addProjectthree(Projectthree projectthree);
}
