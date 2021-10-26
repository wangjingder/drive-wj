package com.wang.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wang.dao.ProjecttwoDao;
import com.wang.pojo.Projecttwo;
import com.wang.pojo.query.ProjecttwoQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProjecttwoServiceImpl implements ProjecttwoService {

    @Resource
    private ProjecttwoDao projecttwoDao;

    @Override
    public List<Projecttwo> listProjecttwo() {
        return projecttwoDao.listProjecttwo();
    }

    @Override
    public PageInfo<Projecttwo> listProjecttwoBytest(ProjecttwoQuery projecttwoQuery) {
        PageHelper.startPage(projecttwoQuery.getPageNum(),projecttwoQuery.getPageSize());
        return new PageInfo<Projecttwo>(projecttwoDao.listProjecttwoBytest(projecttwoQuery));
    }

    @Override
    public boolean deleteProjecttwoById(Integer id) {
        int i = projecttwoDao.deleteProjecttwoById(id);
        if(i > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Projecttwo queryProjecttwoById(Integer id) {
        return projecttwoDao.queryProjecttwoById(id);
    }

    @Override
    public boolean updateProjecttwo(Projecttwo projecttwo) {
        int i = projecttwoDao.updateProjecttwo(projecttwo);
        if(i > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean addProjecttwo(Projecttwo projecttwo) {
        return projecttwoDao.addProjecttwo(projecttwo) > 0 ? true : false ;
    }
}
