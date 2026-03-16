package com.itheima.service;

import com.itheima.pojo.Award;
import com.itheima.pojo.PageResult;

import java.util.List;

public interface AwardService {
    
    PageResult<Award> list(Integer competitionId, Integer studentId, String awardLevel, Integer page, Integer pageSize);
    
    Award findById(Integer id);
    
    void add(Award award);
    
    void update(Award award);
    
    void delete(Integer id);
    
    List<Award> findByStudentId(Integer studentId);
}
