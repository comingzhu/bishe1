package com.itheima.mapper;

import com.itheima.pojo.TeamMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface TeamMemberMapper {

    @Select("SELECT * FROM team_member WHERE id = #{id}")
    TeamMember findById(Integer id);

    List<TeamMember> list(Integer teamId, Integer page, Integer pageSize);

    void insert(TeamMember teamMember);

    void delete(Integer id);

    @Select("SELECT * FROM team_member WHERE team_id = #{teamId} AND student_id = #{studentId}")
    TeamMember findByTeamAndStudent(Integer teamId, Integer studentId);

    @Select("SELECT * FROM team_member WHERE student_id = #{studentId}")
    List<TeamMember> findByStudentId(Integer studentId);

    void deleteByTeamId(Integer teamId);
}
