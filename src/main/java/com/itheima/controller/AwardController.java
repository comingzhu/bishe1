package com.itheima.controller;

import com.itheima.pojo.Award;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.AwardService;
import com.itheima.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/awards")
public class AwardController {

    @Autowired
    private AwardService awardService;

    @GetMapping
    public Result list(@RequestParam(required = false) Integer competitionId,
                      @RequestParam(required = false) Integer studentId,
                      @RequestParam(required = false) String awardLevel,
                      @RequestParam(defaultValue = "1") Integer page,
                      @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("查询获奖列表：competitionId={}, studentId={}, awardLevel={}", competitionId, studentId, awardLevel);
        PageResult<Award> pageResult = awardService.list(competitionId, studentId, awardLevel, page, pageSize);
        return Result.success(pageResult);
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        log.info("查询获奖详情：{}", id);
        Award award = awardService.findById(id);
        return Result.success(award);
    }

    @GetMapping("/my")
    public Result myAwards() {
        log.info("查询我的获奖");
        Integer currentUserId = CurrentHolder.getCurrentId();
        List<Award> awards = awardService.findByStudentId(currentUserId);
        return Result.success(awards);
    }

    @PostMapping
    public Result add(@RequestBody Award award) {
        log.info("录入获奖：competitionId={}, studentId={}", award.getCompetitionId(), award.getStudentId());
        awardService.add(award);
        return Result.success("录入成功");
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable Integer id, @RequestBody Award award) {
        log.info("更新获奖：{}", id);
        award.setId(id);
        awardService.update(award);
        return Result.success("更新成功");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("删除获奖：{}", id);
        awardService.delete(id);
        return Result.success("删除成功");
    }
}
