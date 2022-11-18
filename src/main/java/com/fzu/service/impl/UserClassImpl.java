package com.fzu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzu.entity.User;
import com.fzu.entity.UserClass;
import com.fzu.mapper.UserClassMapper;
import com.fzu.service.UserClassService;
import com.fzu.service.UserService;
import com.fzu.utils.Page;
import com.fzu.vo.UserClassPageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserClassImpl extends ServiceImpl<UserClassMapper, UserClass> implements UserClassService {

    @Autowired
    private UserService userService;

    @Override
    public UserClass getBySid(String sid) {
        QueryWrapper<UserClass> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sid", sid);
        return this.getOne(queryWrapper);
    }


    /**
     * 查找同班同学
     *
     * @param page
     * @param pageVO
     * @return
     */
    @Override
    public Page<User> findPage(Page<User> page, UserClassPageVO pageVO) {
        LambdaQueryWrapper<UserClass> userClassQueryWrapper = new LambdaQueryWrapper<>();
        if (pageVO.getCid() == null) {
            return page; //未接收到班级信息，返回一个空page
        }
        String cid = pageVO.getCid();
        userClassQueryWrapper.eq(UserClass::getCid, cid);//根据班级id进行查找
        List<UserClass> userClassList = this.list(userClassQueryWrapper);
        List<String> userIdList = userClassList.stream().map(UserClass::getUid).collect(Collectors.toList());

        LambdaQueryWrapper<User> userQueryWrapper = new LambdaQueryWrapper<>();
        userQueryWrapper.in(User::getId, userIdList);
        //执行查询
        List<User> userslist = userService.list(userQueryWrapper);
        log.info(userslist.toString());
        page.setCount(userslist.size()); //设置页面总数
        page.setPageSize(pageVO.getPageSize());//第几页

        long firstResult = (long) (page.getPageNo() - 1) * page.getPageSize();
        if (firstResult >= page.getCount()) {
            return page; //返回一个空page;
        }
        page.setPageNo(page.getFirstResult());
        userslist = userslist.stream().skip(firstResult).limit(pageVO.getPageSize()).collect(Collectors.toList());

        page.setList(userslist);

        return page;
    }
}
