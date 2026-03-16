package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.AwardMapper;
import com.itheima.pojo.Award;
import com.itheima.pojo.PageResult;
import com.itheima.service.AwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AwardServiceImpl implements AwardService {

    @Autowired
    private AwardMapper awardMapper;

    @Override
    public PageResult<Award> list(Integer competitionId, Integer studentId, String awardLevel, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Award> list = awardMapper.list(competitionId, studentId, awardLevel, page, pageSize);
        Page<Award> p = (Page<Award>) list;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Override
    public Award findById(Integer id) {
        return awardMapper.findById(id);
    }

    @Override
    public void add(Award award) {
        awardMapper.insert(award);
    }

    @Override
    public void update(Award award) {
        awardMapper.update(award);
    }

    @Override
    public void delete(Integer id) {
        awardMapper.delete(id);
    }

    @Override
    public List<Award> findByStudentId(Integer studentId) {
        return awardMapper.findByStudentId(studentId);
    }
}
