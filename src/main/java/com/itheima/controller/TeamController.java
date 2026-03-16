package com.itheima.controller;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.pojo.Team;
import com.itheima.pojo.TeamMember;
import com.itheima.service.TeamService;
import com.itheima.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping
    public Result list(@RequestParam(required = false) Integer competitionId,
                      @RequestParam(required = false) String status,
                      @RequestParam(defaultValue = "1") Integer page,
                      @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("查询团队列表：competitionId={}, status={}", competitionId, status);
        PageResult<Team> pageResult = teamService.list(competitionId, status, page, pageSize);
        return Result.success(pageResult);
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        log.info("查询团队详情：{}", id);
        Team team = teamService.findById(id);
        return Result.success(team);
    }

    @GetMapping("/{id}/members")
    public Result listMembers(@PathVariable Integer id) {
        log.info("查询团队成员：{}", id);
        List<TeamMember> members = teamService.listMembers(id);
        return Result.success(members);
    }

    @PostMapping
    public Result create(@RequestBody Team team) {
        log.info("创建团队：{}", team.getName());
        
        Integer currentUserId = CurrentHolder.getCurrentId();
        team.setLeaderId(currentUserId);
        team.setStatus("forming");
        
        teamService.create(team);
        return Result.success("创建成功");
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable Integer id, @RequestBody Team team) {
        log.info("更新团队：{}", id);
        team.setId(id);
        teamService.update(team);
        return Result.success("更新成功");
    }

    @PostMapping("/{id}/members")
    public Result addMember(@PathVariable Integer id, @RequestParam Integer studentId) {
        log.info("添加团队成员：teamId={}, studentId={}", id, studentId);
        teamService.addMember(id, studentId);
        return Result.success("添加成功");
    }

    @DeleteMapping("/{id}/members/{studentId}")
    public Result removeMember(@PathVariable Integer id, @PathVariable Integer studentId) {
        log.info("移除团队成员：teamId={}, studentId={}", id, studentId);
        teamService.removeMember(id, studentId);
        return Result.success("移除成功");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("删除团队：{}", id);
        teamService.delete(id);
        return Result.success("删除成功");
    }
}
