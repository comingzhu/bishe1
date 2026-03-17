package com.itheima.controller;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Registration;
import com.itheima.pojo.Result;
import com.itheima.service.RegistrationService;
import com.itheima.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping
    public Result list(@RequestParam(required = false) Integer competitionId,
                      @RequestParam(required = false) String status,
                      @RequestParam(defaultValue = "1") Integer page,
                      @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("查询报名列表：competitionId={}, status={}", competitionId, status);
        PageResult<Registration> pageResult = registrationService.list(competitionId, status, page, pageSize);
        return Result.success(pageResult);
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        log.info("查询报名详情：{}", id);
        Registration registration = registrationService.findByIdWithAward(id);
        return Result.success(registration);
    }

    @PostMapping
    public Result register(@RequestBody Registration registration) {
        log.info("学生报名：{}", registration.getCompetitionId());
        
        Integer currentUserId = CurrentHolder.getCurrentId();
        registration.setStudentId(currentUserId);
        
        registrationService.register(registration);
        return Result.success("报名成功，请等待审核");
    }

    @PutMapping("/{id}/audit")
    public Result audit(@PathVariable Integer id,
                      @RequestParam String status,
                      @RequestParam(required = false) String remark) {
        log.info("审核报名：id={}, status={}", id, status);
        
        Integer currentUserId = CurrentHolder.getCurrentId();
        registrationService.audit(id, status, currentUserId, remark);
        
        return Result.success("审核成功");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("取消报名：{}", id);
        registrationService.delete(id);
        return Result.success("取消成功");
    }
}
