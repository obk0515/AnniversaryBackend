package com.fzu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fzu.entity.Class;
import com.fzu.entity.User;
import com.fzu.entity.UserClass;
import com.fzu.result.ServiceResult;
import com.fzu.service.ClassService;
import com.fzu.service.UserClassService;
import com.fzu.utils.Page;
import com.fzu.vo.UserClassPageVO;
import com.fzu.vo.UserClassVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/UserClass")
@Api(value = "UserClassController", tags = "加入班级模块")
public class UserClassController {

    @Autowired
    private ClassService classService;
    @Autowired
    private UserClassService userClassService;

    /**
     * 加入班级
     *
     * @param userClassVO
     * @return
     */
    @ApiOperation(value = "加入班级")
    @PostMapping("/add")
    public ServiceResult<Class> joinClass(@Validated @RequestBody UserClassVO userClassVO) {
        log.info(userClassVO.toString());
        UserClass userClass = new UserClass();
        // 找到班级
        LambdaQueryWrapper<Class> classQueryWrapper = new LambdaQueryWrapper<>();
        if (userClassVO.getAcademy() != null &&
                userClassVO.getStage() != null &&
                userClassVO.getGrade() != null &&
                userClassVO.getMajor() != null &&
                userClassVO.getClassNo() != null) {
            //根据年级查找
            classQueryWrapper.eq(Class::getAcademy, userClassVO.getAcademy());
            classQueryWrapper.eq(Class::getStage, userClassVO.getStage());
            classQueryWrapper.eq(Class::getGrade, userClassVO.getGrade());
            classQueryWrapper.eq(Class::getMajor, userClassVO.getMajor());
            classQueryWrapper.eq(Class::getClassNo, userClassVO.getClassNo());
        } else {
            log.info("班级不存在");
            return ServiceResult.createByErrorMessage("班级不存在");
        }
        //获取所在班级
        Class cclass = classService.getOne(classQueryWrapper);
        if (cclass == null) {
            log.info("未查询到班级");
            return ServiceResult.createByErrorMessage("未查询到班级");
        }
        //在学生-班级表存储
        userClass.setUid(userClassVO.getUid());
        userClass.setSid(userClassVO.getSid());
        userClass.setCid(cclass.getCid());
        userClassService.save(userClass);
        return ServiceResult.createBySuccess(cclass);
    }

    /**
     * 退出班级
     *
     * @param userClassVO
     * @return
     */
    @ApiOperation("退出班级")
    @DeleteMapping("/delete")
    public ServiceResult<String> exitClass(@Validated @RequestBody UserClassVO userClassVO) {
        LambdaQueryWrapper<UserClass> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserClass::getCid, userClassVO.getCid());
        queryWrapper.eq(UserClass::getUid, userClassVO.getUid());
        userClassService.remove(queryWrapper);
        return ServiceResult.createBySuccessMessage("删除成功");
    }

    @ApiOperation(value = "查看同学")
    @PostMapping("/page")
    public ServiceResult<User> queryClassmate(@RequestBody @ApiParam(value = "UserClassPageVO") UserClassPageVO pageVO) {
        Page<User> page = new Page<>(pageVO.getPageNo(), pageVO.getPageSize());
        Page<User> returnPage = userClassService.findPage(page, pageVO);
        return ServiceResult.createBySuccess(returnPage.getList(), Math.toIntExact(returnPage.getCount()));
    }

    /**
     * 获取学历列表
     *
     * @return
     */
    @ApiOperation(value = "获取学历列表")
    @PostMapping("/getStage")
    public ServiceResult<String> getStage() {
        List<Class> list = classService.list();
        Set<String> set = new HashSet<>();
        for (Class aClass : list) {
            set.add(aClass.getStage());
        }
        List<String> stageList = new ArrayList<>(set);
        return ServiceResult.createBySuccess(stageList, stageList.size());
    }


    /**
     * 获取年级列表
     *
     * @param stage
     * @return
     */
    @ApiOperation(value = "获取年级列表")
    @PostMapping("/getGrade/{stage}")
    public ServiceResult<String> getGrade(@PathVariable String stage) {
        QueryWrapper<Class> wrapper = new QueryWrapper<>();
        wrapper.eq("stage", stage);
        wrapper.orderByAsc("grade");
        List<Class> list = classService.list(wrapper);
        Set<String> set = new HashSet<>();
        for (Class aClass : list) {
            set.add(aClass.getGrade());
        }
        List<String> gradeList = new ArrayList<>(set);
        return ServiceResult.createBySuccess(gradeList, gradeList.size());
    }

    /**
     * 获取学院列表
     *
     * @param stage
     * @param grade
     * @return
     */
    @ApiOperation(value = "获取学院列表")
    @PostMapping("/getAcademy")
    public ServiceResult<String> getAcademy(
            @ApiParam @RequestParam String stage,
            @ApiParam @RequestParam String grade
    ) {
        QueryWrapper<Class> wrapper = new QueryWrapper<>();
        wrapper.eq("stage", stage);
        wrapper.eq("grade", grade);
        List<Class> list = classService.list(wrapper);
        Set<String> set = new HashSet<>();
        for (Class aClass : list) {
            set.add(aClass.getAcademy());
        }
        List<String> academyList = new ArrayList<>(set);
        return ServiceResult.createBySuccess(academyList, academyList.size());
    }

    /**
     * 获取专业列表
     *
     * @param stage
     * @param grade
     * @param academy
     * @return
     */
    @ApiOperation(value = "获取专业列表")
    @PostMapping("/getMajor")
    public ServiceResult<String> getMajor(
            @ApiParam @RequestParam String stage,
            @ApiParam @RequestParam String grade,
            @ApiParam @RequestParam String academy
    ) {
        QueryWrapper<Class> wrapper = new QueryWrapper<>();
        wrapper.eq("stage", stage);
        wrapper.eq("grade", grade);
        wrapper.eq("academy", academy);
        List<Class> list = classService.list(wrapper);
        Set<String> set = new HashSet<>();
        for (Class aClass : list) {
            set.add(aClass.getMajor());
        }
        List<String> gradeList = new ArrayList<>(set);
        return ServiceResult.createBySuccess(gradeList, gradeList.size());
    }

    /**
     * 获取班级列表
     *
     * @param stage
     * @param grade
     * @param academy
     * @param major
     * @return
     */
    @ApiOperation(value = "获取班级列表")
    @PostMapping("/getClassNo")
    public ServiceResult<Integer> getClassNo(
            @ApiParam @RequestParam String stage,
            @ApiParam @RequestParam String grade,
            @ApiParam @RequestParam String academy,
            @ApiParam @RequestParam String major
    ) {
        QueryWrapper<Class> wrapper = new QueryWrapper<>();
        wrapper.eq("stage", stage);
        wrapper.eq("grade", grade);
        wrapper.eq("academy", academy);
        wrapper.eq("major", major);
        wrapper.orderByAsc("class_no");
        List<Class> list = classService.list(wrapper);
        Set<Integer> set = new HashSet<>();
        for (Class aClass : list) {
            set.add(aClass.getClassNo());
        }
        List<Integer> classNoList = new ArrayList<>(set);
        return ServiceResult.createBySuccess(classNoList, classNoList.size());
    }
}
