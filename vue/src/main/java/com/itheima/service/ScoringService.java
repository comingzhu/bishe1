package com.itheima.service;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Scoring;

public interface ScoringService {
    
    PageResult<Scoring> list(Integer workId, Integer scorerId, Integer page, Integer pageSize);
    
    Scoring findById(Integer id);
    
    void score(Scoring scoring);
    
    void update(Scoring scoring);
    
    void delete(Integer id);
}
