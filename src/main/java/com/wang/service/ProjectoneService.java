package com.wang.service;

import com.github.pagehelper.PageInfo;
import com.wang.pojo.Projectone;
import com.wang.pojo.query.ProjectoneQuery;

import java.util.List;

public interface ProjectoneService {

    public List<Projectone> listProjectone();

    public PageInfo<Projectone> listProjectoneBytest(ProjectoneQuery projectoneQuery);

    public boolean deleteProjectoneById(Integer id);

    public Projectone queryProjectoneById(Integer id);

    public boolean updateProjectone(Projectone projectone);

    public boolean addProjectone(Projectone projectone);
}
