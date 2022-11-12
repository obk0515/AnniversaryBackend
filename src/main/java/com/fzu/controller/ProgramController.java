package com.fzu.controller;

import com.fzu.entity.Program;
import com.fzu.result.ServiceResult;
import com.fzu.service.ProgramService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("program")
@Api(value = "ProgramController", tags = "节目单")
public class ProgramController {

    @Autowired
    private ProgramService programService;

    //获取活动清单
    @GetMapping
    public ServiceResult<Program> getAll() {
        return ServiceResult.createBySuccessList(programService.list());
    }

}
