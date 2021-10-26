package com.wang.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wang.dao.ProjectsixDao;
import com.wang.pojo.Projectsix;
import com.wang.pojo.query.ProjectsixQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProjectsixServiceImpl implements ProjectsixService {

    @Resource
    private ProjectsixDao projectsixDao;

    @Override
    public List<Projectsix> listProjectsix() {
        return projectsixDao.listProjectsix();
    }

    @Override
    public PageInfo<Projectsix> listProjectsixBytest(ProjectsixQuery projectsixQuery) {
        PageHelper.startPage(projectsixQuery.getPageNum(),projectsixQuery.getPageSize());
        return new PageInfo<Projectsix>(projectsixDao.listProjectsixBytest(projectsixQuery));
    }

    @Override
    public boolean deleteProjectsixById(Integer id) {
        int i = projectsixDao.deleteProjectsixById(id);
        if(i > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Projectsix queryProjectsixById(Integer id) {
        return projectsixDao.queryProjectsixById(id);
    }

    @Override
    public boolean updateProjectsix(Projectsix projectsix) {
        int i = projectsixDao.updateProjectsix(projectsix);
        if(i > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean addProjectsix(Projectsix projectsix) {
        return projectsixDao.addProjectsix(projectsix) > 0 ? true : false ;
    }

}
