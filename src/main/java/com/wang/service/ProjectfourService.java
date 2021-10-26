package com.wang.service;

import com.github.pagehelper.PageInfo;
import com.wang.pojo.Projectfour;
import com.wang.pojo.query.ProjectfourQuery;

import java.util.List;

public interface ProjectfourService {

    public List<Projectfour> listProjectfour();

    public PageInfo<Projectfour> listProjectfourBytest(ProjectfourQuery projectfourQuery);

    public boolean deleteProjectfourById(Integer id);

    public Projectfour queryProjectfourById(Integer id);

    public boolean updateProjectfour(Projectfour projectfour);

    public boolean addProjectfour(Projectfour projectfour);
}
