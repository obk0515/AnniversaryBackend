package com.fzu.controller;


import com.fzu.entity.Background;
import com.fzu.result.ServiceResult;
import com.fzu.service.BackgroundService;
import com.fzu.utils.Page;
import com.fzu.vo.BackgroundPageVO;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/background")
@Api(value = "BackgroundController", tags = "虚拟背景模块")
public class BackgroundController {

    @Autowired
    private BackgroundService backgroundService;

    @ApiOperation(value = "获取指定虚拟背景")
    @GetMapping("/select")
    @ApiImplicitParam(paramType="query", name = "id", value = "id（例如：1587852585767624705）", required = true, dataType = "String")
    public ServiceResult<Background> getById(@RequestParam String id) {
        Background background = backgroundService.getById(id);
        if(background == null) {
            return ServiceResult.createByErrorMessage("获取失败");
        }
        return ServiceResult.createBySuccess(background);
    }

    @ApiOperation(value = "获取所有虚拟背景")
    @PostMapping("/page")
    public ServiceResult<Background> getAll(@RequestBody @ApiParam (value = "BackgroundPageVO") BackgroundPageVO pageVO){
        Page<Background> page = new Page<>(pageVO.getPageNo(), pageVO.getPageSize());
        Page<Background> returnPage = backgroundService.findPage(page, pageVO);
        return ServiceResult.createBySuccess(returnPage.getList(), Math.toIntExact(returnPage.getCount()));
    }
}
