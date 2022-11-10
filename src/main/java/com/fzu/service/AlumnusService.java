package com.fzu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fzu.entity.Alumnus;

import java.util.List;

public interface AlumnusService extends IService<Alumnus> {

    //通过名字获取校友
    public Alumnus getById(String id);

    //重构List,通过名字获取校友信息
    public List<Alumnus> list(Alumnus alumnus);

}
