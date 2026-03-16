package com.itheima.controller;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.pojo.Scoring;
import com.itheima.service.ScoringService;
import com.itheima.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/scorings")
public class ScoringController {

    @Autowired
    private ScoringService scoringService;

    @GetMapping
    public Result list(@RequestParam(required = false) Integer workId,
                      @RequestParam(required = false) Integer scorerId,
                      @RequestParam(defaultValue = "1") Integer page,
                      @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("查询评分列表：workId={}, scorerId={}", workId, scorerId);
        PageResult<Scoring> pageResult = scoringService.list(workId, scorerId, page, pageSize);
        return Result.success(pageResult);
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        log.info("查询评分详情：{}", id);
        Scoring scoring = scoringService.findById(id);
        return Result.success(scoring);
    }

    @PostMapping
    public Result score(@RequestBody Scoring scoring) {
        log.info("评分：workId={}, score={}", scoring.getWorkId(), scoring.getScore());
        
        Integer currentUserId = CurrentHolder.getCurrentId();
        scoring.setScorerId(currentUserId);
        
        scoringService.score(scoring);
        return Result.success("评分成功");
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable Integer id, @RequestBody Scoring scoring) {
        log.info("更新评分：{}", id);
        scoring.setId(id);
        scoringService.update(scoring);
        return Result.success("更新成功");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("删除评分：{}", id);
        scoringService.delete(id);
        return Result.success("删除成功");
    }
}
