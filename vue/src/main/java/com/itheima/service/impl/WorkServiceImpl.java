package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.WorkMapper;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Work;
import com.itheima.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkServiceImpl implements WorkService {

    @Autowired
    private WorkMapper workMapper;

    @Override
    public PageResult<Work> list(Integer competitionId, Integer studentId, String status, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Work> list = workMapper.list(competitionId, studentId, status, page, pageSize);
        Page<Work> p = (Page<Work>) list;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Override
    public Work findById(Integer id) {
        return workMapper.findById(id);
    }

    @Override
    public void submit(Work work) {
        workMapper.insert(work);
    }

    @Override
    public void update(Work work) {
        workMapper.update(work);
    }

    @Override
    public void delete(Integer id) {
        workMapper.delete(id);
    }
}
