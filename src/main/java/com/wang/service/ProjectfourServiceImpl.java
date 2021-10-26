package com.wang.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wang.dao.ProjectfourDao;
import com.wang.pojo.Projectfour;
import com.wang.pojo.query.ProjectfourQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProjectfourServiceImpl implements ProjectfourService {

    @Resource
    private ProjectfourDao projectfourDao;

    @Override
    public List<Projectfour> listProjectfour() {
        return projectfourDao.listProjectfour();
    }

    @Override
    public PageInfo<Projectfour> listProjectfourBytest(ProjectfourQuery projectfourQuery) {
        PageHelper.startPage(projectfourQuery.getPageNum(),projectfourQuery.getPageSize());
        return new PageInfo<Projectfour>(projectfourDao.listProjectfourBytest(projectfourQuery));
    }

    @Override
    public boolean deleteProjectfourById(Integer id) {
        int i = projectfourDao.deleteProjectfourById(id);
        if(i > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Projectfour queryProjectfourById(Integer id) {
        return projectfourDao.queryProjectfourById(id);
    }

    @Override
    public boolean updateProjectfour(Projectfour projectfour) {
        int i = projectfourDao.updateProjectfour(projectfour);
        if(i > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean addProjectfour(Projectfour projectfour) {
        return projectfourDao.addProjectfour(projectfour) > 0 ? true : false ;
    }

}
