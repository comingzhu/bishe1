package com.itheima.mapper;

import com.itheima.pojo.Registration;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface RegistrationMapper {

    @Select("SELECT * FROM registration WHERE id = #{id}")
    Registration findById(Integer id);

    Registration findByIdWithAward(Integer id);

    List<Registration> list(Integer competitionId, String status, Integer page, Integer pageSize);

    void insert(Registration registration);

    void update(Registration registration);

    void delete(Integer id);

    @Select("SELECT COUNT(*) FROM registration WHERE competition_id = #{competitionId}")
    Integer countByCompetitionId(Integer competitionId);

    @Select("SELECT * FROM registration WHERE student_id = #{studentId} AND competition_id = #{competitionId}")
    Registration findByStudentAndCompetition(Integer studentId, Integer competitionId);
}
