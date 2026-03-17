package com.itheima.mapper;

import com.itheima.pojo.Award;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface AwardMapper {

    @Select("SELECT * FROM award WHERE id = #{id}")
    Award findById(Integer id);

    List<Award> list(Integer competitionId, Integer studentId, String awardLevel, Integer page, Integer pageSize);

    void insert(Award award);

    void update(Award award);

    void delete(Integer id);

    @Select("SELECT * FROM award WHERE competition_id = #{competitionId} AND student_id = #{studentId}")
    Award findByCompetitionAndStudent(Integer competitionId, Integer studentId);

    @Select("SELECT * FROM award WHERE student_id = #{studentId}")
    List<Award> findByStudentId(Integer studentId);
}
