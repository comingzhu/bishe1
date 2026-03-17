package com.itheima.controller;

import com.itheima.pojo.Competition;
import com.itheima.pojo.CompetitionQueryParam;
import com.itheima.pojo.CompetitionVO;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.CompetitionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/competitions")
public class CompetitionController {

    @Autowired
    private CompetitionService competitionService;

    @GetMapping
    public Result list(CompetitionQueryParam param) {
        log.info("查询竞赛列表：{}", param);
        
        if (param.getPage() == null) param.setPage(1);
        if (param.getPageSize() == null) param.setPageSize(10);
        
        PageResult<CompetitionVO> pageResult = competitionService.list(param);
        return Result.success(pageResult);
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        log.info("查询竞赛详情：{}", id);
        CompetitionVO competition = competitionService.findById(id);
        return Result.success(competition);
    }

    @PostMapping
    public Result add(@RequestBody Competition competition) {
        log.info("发布竞赛：{}", competition.getTitle());
        competitionService.add(competition);
        return Result.success("发布成功");
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable Integer id, @RequestBody Competition competition) {
        log.info("更新竞赛：{}", id);
        competition.setId(id);
        competitionService.update(competition);
        return Result.success("更新成功");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("删除竞赛：{}", id);
        competitionService.delete(id);
        return Result.success("删除成功");
    }
}
