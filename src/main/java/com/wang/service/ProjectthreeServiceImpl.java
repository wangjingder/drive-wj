package com.wang.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wang.dao.ProjectthreeDao;
import com.wang.pojo.Projectthree;
import com.wang.pojo.query.ProjectthreeQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProjectthreeServiceImpl implements ProjectthreeService {

    @Resource
    private ProjectthreeDao projectthreeDao;

    @Override
    public List<Projectthree> listProjectthree() {
        return projectthreeDao.listProjectthree();
    }

    @Override
    public PageInfo<Projectthree> listProjectthreeBytest(ProjectthreeQuery projectthreeQuery) {
        PageHelper.startPage(projectthreeQuery.getPageNum(),projectthreeQuery.getPageSize());
        return new PageInfo<Projectthree>(projectthreeDao.listProjectthreeBytest(projectthreeQuery));
    }

    @Override
    public boolean deleteProjectthreeById(Integer id) {
        int i = projectthreeDao.deleteProjectthreeById(id);
        if(i > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Projectthree queryProjectthreeById(Integer id) {
        return projectthreeDao.queryProjectthreeById(id);
    }

    @Override
    public boolean updateProjectthree(Projectthree projectthree) {
        int i = projectthreeDao.updateProjectthree(projectthree);
        if(i > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean addProjectthree(Projectthree projectthree) {
        return projectthreeDao.addProjectthree(projectthree) > 0 ? true : false ;
    }

}
