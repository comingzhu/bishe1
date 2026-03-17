package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.TeamMapper;
import com.itheima.mapper.TeamMemberMapper;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Team;
import com.itheima.pojo.TeamMember;
import com.itheima.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private TeamMemberMapper teamMemberMapper;

    @Override
    public PageResult<Team> list(Integer competitionId, String status, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Team> list = teamMapper.list(competitionId, status, page, pageSize);
        Page<Team> p = (Page<Team>) list;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Override
    public Team findById(Integer id) {
        return teamMapper.findById(id);
    }

    @Override
    @Transactional
    public void create(Team team) {
        teamMapper.insert(team);
    }

    @Override
    public void update(Team team) {
        teamMapper.update(team);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        teamMemberMapper.deleteByTeamId(id);
        teamMapper.delete(id);
    }

    @Override
    @Transactional
    public void addMember(Integer teamId, Integer studentId) {
        Team team = teamMapper.findById(teamId);
        Integer currentMembers = teamMapper.countMembers(teamId);
        if (currentMembers >= team.getMaxMembers()) {
            throw new RuntimeException("团队人数已满");
        }
        
        TeamMember member = new TeamMember();
        member.setTeamId(teamId);
        member.setStudentId(studentId);
        member.setRole("member");
        teamMemberMapper.insert(member);
    }

    @Override
    @Transactional
    public void removeMember(Integer teamId, Integer studentId) {
        TeamMember member = teamMemberMapper.findByTeamAndStudent(teamId, studentId);
        if (member != null) {
            teamMemberMapper.delete(member.getId());
        }
    }

    @Override
    public List<TeamMember> listMembers(Integer teamId) {
        return teamMemberMapper.list(teamId, null, null);
    }
}
