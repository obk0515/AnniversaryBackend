package com.fzu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fzu.entity.Program;
import com.fzu.mapper.ProgramMapper;
import com.fzu.service.ProgramService;
import com.fzu.utils.Page;
import com.fzu.vo.ProgramPageVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProgramImpl extends com.baomidou.mybatisplus.extension.service.impl.ServiceImpl<ProgramMapper, Program>
        implements ProgramService {
    @Override
    public Page<Program> findPage(Page<Program> page, ProgramPageVo pageVO) {
        QueryWrapper<Program> wrapper = new QueryWrapper();
        wrapper.orderByDesc("priority");
        List<Program> list = this.list(wrapper);
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
}
