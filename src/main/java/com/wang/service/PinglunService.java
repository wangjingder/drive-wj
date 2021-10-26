package com.wang.service;

import com.github.pagehelper.PageInfo;
import com.wang.pojo.Pinglun;
import com.wang.pojo.Quanzi;
import com.wang.pojo.User;
import com.wang.pojo.query.PinglunQuery;

import java.util.List;

public interface PinglunService {

    public List<Pinglun> listPinglun();

    public PageInfo<Pinglun> listPinglunBytest(PinglunQuery pinglunQuery);

    public boolean deletePinglunById(Integer id);

    public Pinglun queryPinglunById(Integer id);

    public boolean updatePinglun(Pinglun pinglun);

    public boolean addPinglun(Pinglun pinglun);

    List<Pinglun> queryPinglun(String city);

    boolean pinglunRegister(Pinglun pinglun);
}
