package com.wang.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wang.dao.ProjectoneDao;
import com.wang.pojo.Projectone;
import com.wang.pojo.query.ProjectoneQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProjectoneServiceImpl implements ProjectoneService {

    @Resource
    private ProjectoneDao projectoneDao;

    @Override
    public List<Projectone> listProjectone() {
        return projectoneDao.listProjectone();
    }

    @Override
    public PageInfo<Projectone> listProjectoneBytest(ProjectoneQuery projectoneQuery) {
        PageHelper.startPage(projectoneQuery.getPageNum(),projectoneQuery.getPageSize());
        return new PageInfo<Projectone>(projectoneDao.listProjectoneBytest(projectoneQuery));
    }

    @Override
    public boolean deleteProjectoneById(Integer id) {
        int i = projectoneDao.deleteProjectoneById(id);
        if(i > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Projectone queryProjectoneById(Integer id) {
        return projectoneDao.queryProjectoneById(id);
    }

    @Override
    public boolean updateProjectone(Projectone projectone) {
        int i = projectoneDao.updateProjectone(projectone);
        if(i > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean addProjectone(Projectone projectone) {
        return projectoneDao.addProjectone(projectone) > 0 ? true : false ;
    }

}
