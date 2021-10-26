package com.wang.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wang.dao.PinglunDao;
import com.wang.pojo.Pinglun;
import com.wang.pojo.Quanzi;
import com.wang.pojo.User;
import com.wang.pojo.query.PinglunQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PinglunServiceImpl implements PinglunService {

    @Resource
    private PinglunDao pinglunDao;

    @Override
    public List<Pinglun> listPinglun() {
        return pinglunDao.listPinglun();
    }

    @Override
    public PageInfo<Pinglun> listPinglunBytest(PinglunQuery pinglunQuery) {
        PageHelper.startPage(pinglunQuery.getPageNum(),pinglunQuery.getPageSize());
        return new PageInfo<Pinglun>(pinglunDao.listPinglunBytest(pinglunQuery));
    }

    @Override
    public boolean deletePinglunById(Integer id) {
        int i = pinglunDao.deletePinglunById(id);
        if(i > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Pinglun queryPinglunById(Integer id) {
        return pinglunDao.queryPinglunById(id);
    }

    @Override
    public boolean updatePinglun(Pinglun pinglun) {
        int i = pinglunDao.updatePinglun(pinglun);
        if(i > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean addPinglun(Pinglun pinglun) {
        return pinglunDao.addPinglun(pinglun) > 0 ? true : false ;
    }

    @Override
    public List<Pinglun> queryPinglun(String city) {
        return pinglunDao.queryPinglun(city);
    }

    @Override
    public boolean pinglunRegister(Pinglun pinglun) {
        return pinglunDao.pinglunRegister(pinglun);
    }

}
