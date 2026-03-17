package com.itheima.service;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Team;
import com.itheima.pojo.TeamMember;
import java.util.List;

public interface TeamService {
    
    PageResult<Team> list(Integer competitionId, String status, Integer page, Integer pageSize);
    
    Team findById(Integer id);
    
    void create(Team team);
    
    void update(Team team);
    
    void delete(Integer id);
    
    void addMember(Integer teamId, Integer studentId);
    
    void removeMember(Integer teamId, Integer studentId);
    
    List<TeamMember> listMembers(Integer teamId);
}
