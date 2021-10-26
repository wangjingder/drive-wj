package com.wang.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wang.dao.ProjectfiveDao;
import com.wang.pojo.Projectfive;
import com.wang.pojo.query.ProjectfiveQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProjectfiveServiceImpl implements ProjectfiveService {

    @Resource
    private ProjectfiveDao projectfiveDao;

    @Override
    public List<Projectfive> listProjectfive() {
        return projectfiveDao.listProjectfive();
    }

    @Override
    public PageInfo<Projectfive> listProjectfiveBytest(ProjectfiveQuery projectfiveQuery) {
        PageHelper.startPage(projectfiveQuery.getPageNum(),projectfiveQuery.getPageSize());
        return new PageInfo<Projectfive>(projectfiveDao.listProjectfiveBytest(projectfiveQuery));
    }

    @Override
    public boolean deleteProjectfiveById(Integer id) {
        int i = projectfiveDao.deleteProjectfiveById(id);
        if(i > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Projectfive queryProjectfiveById(Integer id) {
        return projectfiveDao.queryProjectfiveById(id);
    }

    @Override
    public boolean updateProjectfive(Projectfive projectfive) {
        int i = projectfiveDao.updateProjectfive(projectfive);
        if(i > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean addProjectfive(Projectfive projectfive) {
        return projectfiveDao.addProjectfive(projectfive) > 0 ? true : false ;
    }

}
