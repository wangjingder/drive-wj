package com.wang.service;

import com.github.pagehelper.PageInfo;
import com.wang.pojo.Projecttwo;
import com.wang.pojo.query.ProjecttwoQuery;

import java.util.List;

public interface ProjecttwoService {

    public List<Projecttwo> listProjecttwo();

    public PageInfo<Projecttwo> listProjecttwoBytest(ProjecttwoQuery projecttwoQuery);

    public boolean deleteProjecttwoById(Integer id);

    public Projecttwo queryProjecttwoById(Integer id);

    public boolean updateProjecttwo(Projecttwo projecttwo);

    public boolean addProjecttwo(Projecttwo projecttwo);
}
