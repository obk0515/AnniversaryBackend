package com.fzu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
        if(userClassVO.getGrade() != null) {
            log.info(userClassVO.getGrade());
            classQueryWrapper.eq(Class::getGrade, userClassVO.getGrade()); //根据年级查找
        }
        if(userClassVO.getMajor() != null) {
            log.info(userClassVO.getMajor());
            classQueryWrapper.eq(Class::getMajor, userClassVO.getMajor()); //根据专业查找班级
        }

        if(classQueryWrapper == null) {
            log.info("为查询到班级");
            return ServiceResult.createByErrorMessage("未查询到message");
        }

        if(userClassVO.getClass_no() != null) {
            classQueryWrapper.eq(Class::getClassNo, userClassVO.getClass_no());
        }

        Class cclass = classService.getOne(classQueryWrapper); //获取所在班级

        if(cclass == null) {
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
     * @param userClassVO
     * @return
     */
    @ApiOperation("退出班级")
    @DeleteMapping("/delete")
    public ServiceResult<String> exitClass(@Validated @RequestBody UserClassVO userClassVO) {
        LambdaQueryWrapper<UserClass> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserClass::getCid, userClassVO.getCid());
        queryWrapper.eq(UserClass::getUid, userClassVO.getUid());
        if(queryWrapper == null) {
            return ServiceResult.createByErrorMessage("删除失败");
        }
        userClassService.remove(queryWrapper);
        return ServiceResult.createBySuccessMessage("删除成功");
    }

    @ApiOperation(value = "查看同学")
    @PostMapping("/page")
    public ServiceResult<User> QueryClassmate(@RequestBody @ApiParam(value = "UserClassPageVO") UserClassPageVO pageVO){
        Page<User> page = new Page<>(pageVO.getPageNo(), pageVO.getPageSize());
        Page<User> returnPage = userClassService.findPage(page, pageVO);
        return ServiceResult.createBySuccess(returnPage.getList(), Math.toIntExact(returnPage.getCount()));
    }
}