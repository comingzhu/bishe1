package com.itheima.controller;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.pojo.Work;
import com.itheima.service.WorkService;
import com.itheima.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/works")
public class WorkController {

    @Autowired
    private WorkService workService;

    @GetMapping
    public Result list(@RequestParam(required = false) Integer competitionId,
                      @RequestParam(required = false) Integer studentId,
                      @RequestParam(required = false) String status,
                      @RequestParam(defaultValue = "1") Integer page,
                      @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("查询作品列表：competitionId={}, studentId={}, status={}", competitionId, studentId, status);
        PageResult<Work> pageResult = workService.list(competitionId, studentId, status, page, pageSize);
        return Result.success(pageResult);
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        log.info("查询作品详情：{}", id);
        Work work = workService.findById(id);
        return Result.success(work);
    }

    @PostMapping
    public Result submit(@RequestBody Work work) {
        log.info("提交作品：{}", work.getTitle());
        
        Integer currentUserId = CurrentHolder.getCurrentId();
        work.setStudentId(currentUserId);
        work.setStatus("submitted");
        
        workService.submit(work);
        return Result.success("提交成功");
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable Integer id, @RequestBody Work work) {
        log.info("更新作品：{}", id);
        work.setId(id);
        workService.update(work);
        return Result.success("更新成功");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("删除作品：{}", id);
        workService.delete(id);
        return Result.success("删除成功");
    }
}
