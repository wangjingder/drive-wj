package com.wang.service;

import com.github.pagehelper.PageInfo;
import com.wang.pojo.Projectseven;
import com.wang.pojo.User;
import com.wang.pojo.query.ProjectsevenQuery;

import java.util.List;

public interface ProjectsevenService {

    public List<Projectseven> listProjectseven();

    public PageInfo<Projectseven> listProjectsevenBytest(ProjectsevenQuery projectsevenQuery);

    public boolean deleteProjectsevenById(Integer id);

    public Projectseven queryProjectsevenById(Integer id);

    public boolean updateProjectseven(Projectseven projectseven);

    public boolean addProjectseven(Projectseven projectseven);

    List<Projectseven> queryProjectseven(String city);
}
