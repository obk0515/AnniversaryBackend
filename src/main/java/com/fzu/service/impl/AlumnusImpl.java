package com.fzu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fzu.entity.Alumnus;
import com.fzu.mapper.AlumnusMapper;
import com.fzu.service.AlumnusService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnusImpl extends com.baomidou.mybatisplus.extension.service.impl.ServiceImpl<AlumnusMapper, Alumnus>
implements AlumnusService {

    @Override
    public Alumnus getById(String id) {
        QueryWrapper<Alumnus> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        return this.getOne(wrapper);
    }

    //重构，通过名字得来的Alumnus来查询校友数据
    @Override
    public List<Alumnus> list(Alumnus alumnus) {
        return list(Wrappers.lambdaQuery(alumnus));
    }


}
