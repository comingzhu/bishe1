package com.itheima.service;

import com.itheima.pojo.Competition;
import com.itheima.pojo.CompetitionVO;
import com.itheima.pojo.CompetitionQueryParam;
import com.itheima.pojo.PageResult;

public interface CompetitionService {
    
    PageResult<CompetitionVO> list(CompetitionQueryParam param);
    
    CompetitionVO findById(Integer id);
    
    void add(Competition competition);
    
    void update(Competition competition);
    
    void delete(Integer id);
}
