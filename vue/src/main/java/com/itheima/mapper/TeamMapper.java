package com.itheima.mapper;

import com.itheima.pojo.Team;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface TeamMapper {

    @Select("SELECT * FROM team WHERE id = #{id}")
    Team findById(Integer id);

    List<Team> list(Integer competitionId, String status, Integer page, Integer pageSize);

    void insert(Team team);

    void update(Team team);

    void delete(Integer id);

    @Select("SELECT COUNT(*) FROM team_member WHERE team_id = #{teamId}")
    Integer countMembers(Integer teamId);

    @Select("SELECT * FROM team WHERE competition_id = #{competitionId} AND leader_id = #{leaderId}")
    Team findByCompetitionAndLeader(Integer competitionId, Integer leaderId);
}
