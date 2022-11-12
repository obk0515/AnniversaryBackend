package com.fzu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fzu.entity.Bless;
import com.fzu.entity.User;
import com.fzu.mapper.BlessMapper;
import com.fzu.service.BlessService;
import com.fzu.service.UserService;
import com.fzu.utils.Page;
import com.fzu.vo.BlessPageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlessImpl extends com.baomidou.mybatisplus.extension.service.impl.ServiceImpl<BlessMapper, Bless>
        implements BlessService {

    //用于返回User
    @Autowired
    private UserService userService;

    @Override
    public Page<Bless> findPage(Page<Bless> page, BlessPageVo pageVO) {
        QueryWrapper<Bless> wrapper = new QueryWrapper();
        List<Bless> list = this.list(wrapper);
        //设置总数
        page.setCount(list.size());
        // 每页条数
        page.setPageSize(pageVO.getPageSize());
        // 第几页
        long firstResult = (long) (page.getPageNo() - 1) * page.getPageSize();
        if (firstResult >= page.getCount()) {
            return page;
        }
        page.setPageNo(page.getFirstResult());
        list = list.stream().skip(firstResult).limit(pageVO.getPageSize()).collect(Collectors.toList());
        page.setList(list);
        return page;
    }

    @Override
    public User getById(String id) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        return userService.getOne(wrapper);
    }
}
