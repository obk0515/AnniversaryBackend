package com.fzu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fzu.entity.Program;
import com.fzu.utils.Page;
import com.fzu.vo.ProgramPageVo;

public interface ProgramService extends IService<Program> {

    Page<Program> findPage(Page<Program> page, ProgramPageVo pageVO);
}
