package com.fzu.controller;

import com.fzu.entity.Alumnus;
import com.fzu.result.ServiceResult;
import com.fzu.service.AlumnusService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("alumnus")
@Api(value = "AchievementController", tags = "校友风采")
public class AlumnusController {

    @Autowired
    private AlumnusService alumnusService;

    //获取全部校友信息
    @GetMapping
    public ServiceResult<Alumnus> getAll() {
        return ServiceResult.createBySuccessList(alumnusService.list());
    }

    //按照ID获取校友信息
    @PostMapping("/{id}")
    public ServiceResult<Alumnus> getById(@PathVariable String id) {
        if (alumnusService.getById(id) == null) {
            return ServiceResult.createByErrorMessage("不存在该校友信息");
        }
        Alumnus alumnus = alumnusService.getById(id);
        return ServiceResult.createBySuccessList(alumnusService.list(alumnus));
    }

}
