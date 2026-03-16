package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.CompetitionMapper;
import com.itheima.pojo.Competition;
import com.itheima.pojo.CompetitionQueryParam;
import com.itheima.pojo.CompetitionVO;
import com.itheima.pojo.PageResult;
import com.itheima.service.CompetitionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetitionServiceImpl implements CompetitionService {

    @Autowired
    private CompetitionMapper competitionMapper;

    @Override
    public PageResult<CompetitionVO> list(CompetitionQueryParam param) {
        PageHelper.startPage(param.getPage(), param.getPageSize());
        List<CompetitionVO> list = competitionMapper.list(param);
        Page<CompetitionVO> p = (Page<CompetitionVO>) list;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Override
    public CompetitionVO findById(Integer id) {
        Competition competition = competitionMapper.findById(id);
        CompetitionVO vo = new CompetitionVO();
        BeanUtils.copyProperties(competition, vo);
        return vo;
    }

    @Override
    public void add(Competition competition) {
        competitionMapper.insert(competition);
    }

    @Override
    public void update(Competition competition) {
        competitionMapper.update(competition);
    }

    @Override
    public void delete(Integer id) {
        competitionMapper.delete(id);
    }
}
