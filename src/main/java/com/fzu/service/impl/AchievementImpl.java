package com.fzu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzu.entity.Achievement;
import com.fzu.mapper.AchievementMapper;
import com.fzu.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AchievementImpl extends ServiceImpl<AchievementMapper, Achievement> implements AchievementService {

    @Autowired
    private AchievementMapper achievementMapper;

    @Override
    public List<Achievement> getByType(String type) {
        QueryWrapper<Achievement> wrapper = new QueryWrapper<>();
        wrapper.eq("type",type);
        return achievementMapper.selectList(wrapper);
    }

    @Override
    public List<Achievement> list(Achievement achievement) {
        return list(Wrappers.lambdaQuery(achievement));
    }

    @Override
    public Achievement getType(String type){
        QueryWrapper<Achievement> wrapper = new QueryWrapper<>();
        wrapper.eq("type",type);
        return this.getOne(wrapper);
    }
}
