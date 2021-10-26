package com.wang.service;

import com.github.pagehelper.PageInfo;
import com.wang.pojo.Projectfive;
import com.wang.pojo.query.ProjectfiveQuery;

import java.util.List;

public interface ProjectfiveService {

    public List<Projectfive> listProjectfive();

    public PageInfo<Projectfive> listProjectfiveBytest(ProjectfiveQuery projectfiveQuery);

    public boolean deleteProjectfiveById(Integer id);

    public Projectfive queryProjectfiveById(Integer id);

    public boolean updateProjectfive(Projectfive projectfive);

    public boolean addProjectfive(Projectfive projectfive);
}
