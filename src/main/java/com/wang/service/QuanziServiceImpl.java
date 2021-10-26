package com.wang.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wang.dao.QuanziDao;
import com.wang.pojo.Quanzi;
import com.wang.pojo.User;
import com.wang.pojo.query.QuanziQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QuanziServiceImpl implements QuanziService {

    @Resource
    private QuanziDao quanziDao;

    @Override
    public List<Quanzi> listQuanzi() {
        return quanziDao.listQuanzi();
    }

    @Override
    public PageInfo<Quanzi> listQuanziBytest(QuanziQuery quanziQuery) {
        PageHelper.startPage(quanziQuery.getPageNum(),quanziQuery.getPageSize());
        return new PageInfo<Quanzi>(quanziDao.listQuanziBytest(quanziQuery));
    }

    @Override
    public boolean deleteQuanziById(Integer id) {
        int i = quanziDao.deleteQuanziById(id);
        if(i > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Quanzi queryQuanziById(Integer id) {
        return quanziDao.queryQuanziById(id);
    }

    @Override
    public boolean updateQuanzi(Quanzi quanzi) {
        int i = quanziDao.updateQuanzi(quanzi);
        if(i > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean addQuanzi(Quanzi quanzi) {
        return quanziDao.addQuanzi(quanzi) > 0 ? true : false ;
    }

    @Override
    public boolean quanziRegister(Quanzi quanzi) {
        return quanziDao.quanziRegister(quanzi);
    }
}
