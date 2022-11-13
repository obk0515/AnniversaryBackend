package com.fzu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fzu.entity.Achievement;

import java.util.List;

public interface AchievementService extends IService<Achievement> {

    List<Achievement> getByType(String type);

    List<Achievement> list(Achievement achievement);

}
