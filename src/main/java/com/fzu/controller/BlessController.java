package com.fzu.controller;

import com.fzu.entity.Bless;
import com.fzu.entity.User;
import com.fzu.result.ServiceResult;
import com.fzu.service.BlessService;
//import com.fzu.utils.Results;
import com.fzu.service.UserService;
import com.fzu.utils.Page;
import com.fzu.vo.BlessPageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/bless")
@Api(value = "BlessController",tags = "云祝福")
public class BlessController {

    @Autowired
    private BlessService blessService;

    @Autowired
    private UserService userService;

    //获取祝福语
    @PostMapping
    public ServiceResult<Bless> selectPage(@RequestBody @ApiParam(value = "BlessPageVo") BlessPageVo pageVO,BindingResult bindingResult) {
        //参数验证
        if (bindingResult.hasErrors()) {
            return ServiceResult.createByErrorMessage(String.valueOf(bindingResult.getFieldErrors()));
        }
        Page<Bless> page = new Page<>(pageVO.getPageNo(), pageVO.getPageSize());
        Page<Bless> returnPage = blessService.findPage(page, pageVO);
        return ServiceResult.createBySuccess(returnPage.getList(), Math.toIntExact  (returnPage.getCount()));
    }

    //写入祝福语
    @PostMapping("/save/{id}}")
    public ServiceResult<Bless> saveBless(@RequestBody String blessing, @PathVariable String id){
        //通过User获得创建者名字
        User user = userService.getById(id);
        Bless bless = new Bless();
        bless.setBless(blessing);
        bless.setStatus("0");
        Date date = new Date();
        bless.setCreateTime(date);
        bless.setCreateBy(user.getName());
        blessService.save(bless);
        return ServiceResult.createBySuccess(bless);
    }

}
