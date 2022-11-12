package com.fzu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fzu.entity.Bless;
import com.fzu.entity.User;
import com.fzu.utils.Page;
import com.fzu.vo.BlessPageVo;

public interface BlessService extends IService<Bless> {

    Page<Bless> findPage(Page<Bless> page, BlessPageVo pageVO);

    User getById(String id);
}
