package com.wang.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wang.dao.ProjectsevenDao;
import com.wang.pojo.Projectseven;
import com.wang.pojo.User;
import com.wang.pojo.query.ProjectsevenQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProjectsevenServiceImpl implements ProjectsevenService {

    @Resource
    private ProjectsevenDao projectsevenDao;

    @Override
    public List<Projectseven> listProjectseven() {
        return projectsevenDao.listProjectseven();
    }

    @Override
    public PageInfo<Projectseven> listProjectsevenBytest(ProjectsevenQuery projectsevenQuery) {
        PageHelper.startPage(projectsevenQuery.getPageNum(),projectsevenQuery.getPageSize());
        return new PageInfo<Projectseven>(projectsevenDao.listProjectsevenBytest(projectsevenQuery));
    }

    @Override
    public boolean deleteProjectsevenById(Integer id) {
        int i = projectsevenDao.deleteProjectsevenById(id);
        if(i > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Projectseven queryProjectsevenById(Integer id) {
        return projectsevenDao.queryProjectsevenById(id);
    }

    @Override
    public boolean updateProjectseven(Projectseven projectseven) {
        int i = projectsevenDao.updateProjectseven(projectseven);
        if(i > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean addProjectseven(Projectseven projectseven) {
        return projectsevenDao.addProjectseven(projectseven) > 0 ? true : false ;
    }

    @Override
    public List<Projectseven> queryProjectseven(String city) {
        return projectsevenDao.queryProjectseven(city);
    }

}
