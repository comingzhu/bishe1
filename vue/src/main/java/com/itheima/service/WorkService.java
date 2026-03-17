package com.itheima.service;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Work;

public interface WorkService {
    
    PageResult<Work> list(Integer competitionId, Integer studentId, String status, Integer page, Integer pageSize);
    
    Work findById(Integer id);
    
    void submit(Work work);
    
    void update(Work work);
    
    void delete(Integer id);
}
