package com.wang.service;

import com.github.pagehelper.PageInfo;
import com.wang.pojo.Projectsix;
import com.wang.pojo.query.ProjectsixQuery;

import java.util.List;

public interface ProjectsixService {

    public List<Projectsix> listProjectsix();

    public PageInfo<Projectsix> listProjectsixBytest(ProjectsixQuery projectsixQuery);

    public boolean deleteProjectsixById(Integer id);

    public Projectsix queryProjectsixById(Integer id);

    public boolean updateProjectsix(Projectsix projectsix);

    public boolean addProjectsix(Projectsix projectsix);
}
