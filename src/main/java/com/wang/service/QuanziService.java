package com.wang.service;

import com.github.pagehelper.PageInfo;
import com.wang.pojo.Quanzi;
import com.wang.pojo.query.QuanziQuery;

import java.util.List;

public interface QuanziService {

    public List<Quanzi> listQuanzi();

    public PageInfo<Quanzi> listQuanziBytest(QuanziQuery quanziQuery);

    public boolean deleteQuanziById(Integer id);

    public Quanzi queryQuanziById(Integer id);

    public boolean updateQuanzi(Quanzi quanzi);

    public boolean addQuanzi(Quanzi quanzi);

    boolean quanziRegister(Quanzi quanzi);
}
