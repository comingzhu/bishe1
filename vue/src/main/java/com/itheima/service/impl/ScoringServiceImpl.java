package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.ScoringMapper;
import com.itheima.mapper.WorkMapper;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Scoring;
import com.itheima.service.ScoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoringServiceImpl implements ScoringService {

    @Autowired
    private ScoringMapper scoringMapper;

    @Autowired
    private WorkMapper workMapper;

    @Override
    public PageResult<Scoring> list(Integer workId, Integer scorerId, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Scoring> list = scoringMapper.list(workId, scorerId, page, pageSize);
        Page<Scoring> p = (Page<Scoring>) list;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Override
    public Scoring findById(Integer id) {
        return scoringMapper.findById(id);
    }

    @Override
    public void score(Scoring scoring) {
        scoringMapper.insert(scoring);
    }

    @Override
    public void update(Scoring scoring) {
        scoringMapper.update(scoring);
    }

    @Override
    public void delete(Integer id) {
        scoringMapper.delete(id);
    }
}
