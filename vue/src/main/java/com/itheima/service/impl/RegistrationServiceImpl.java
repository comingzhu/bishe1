package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.RegistrationMapper;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Registration;
import com.itheima.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationMapper registrationMapper;

    @Override
    public PageResult<Registration> list(Integer competitionId, String status, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Registration> list = registrationMapper.list(competitionId, status, page, pageSize);
        Page<Registration> p = (Page<Registration>) list;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Override
    public Registration findById(Integer id) {
        return registrationMapper.findById(id);
    }

    @Override
    public Registration findByIdWithAward(Integer id) {
        return registrationMapper.findByIdWithAward(id);
    }

    @Override
    @Transactional
    public void register(Registration registration) {
        registration.setStatus("pending");
        registrationMapper.insert(registration);
    }

    @Override
    @Transactional
    public void audit(Integer id, String status, Integer auditorId, String remark) {
        Registration registration = new Registration();
        registration.setId(id);
        registration.setStatus(status);
        registration.setAuditTime(LocalDateTime.now());
        registration.setAuditorId(auditorId);
        registration.setRemark(remark);
        registrationMapper.update(registration);
    }

    @Override
    public void delete(Integer id) {
        registrationMapper.delete(id);
    }
}
