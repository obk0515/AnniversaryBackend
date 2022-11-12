package com.fzu.controller;

import com.fzu.entity.Achievement;
import com.fzu.service.AchievementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("achievement")
@Api(value = "AchievementController", tags = "学院风采")
public class AchievementController {
    /**
     * 根据type的值来展示不同的信息，学院简介、学科建设、科研成果
     */
    @Autowired
    private AchievementService achievementService;

    //按照type来查询
    @GetMapping("/{type}")
    @ApiOperation(value = "getByType")
    public List<Achievement> getByType(@PathVariable String type) {
        List<Achievement> achievement = achievementService.getByType(type);

        Achievement achievement1 = achievementService.getType(type);

        return achievementService.list(achievement1);
    }

}
