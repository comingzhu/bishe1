package com.itheima.service;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Registration;

public interface RegistrationService {
    
    PageResult<Registration> list(Integer competitionId, String status, Integer page, Integer pageSize);
    
    Registration findById(Integer id);
    
    Registration findByIdWithAward(Integer id);
    
    void register(Registration registration);
    
    void audit(Integer id, String status, Integer auditorId, String remark);
    
    void delete(Integer id);
}
