package com.itheima.mapper;

import com.itheima.pojo.Work;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface WorkMapper {

    @Select("SELECT * FROM work WHERE id = #{id}")
    Work findById(Integer id);

    List<Work> list(Integer competitionId, Integer studentId, String status, Integer page, Integer pageSize);

    void insert(Work work);

    void update(Work work);

    void delete(Integer id);

    @Select("SELECT * FROM work WHERE registration_id = #{registrationId}")
    Work findByRegistrationId(Integer registrationId);

    @Select("SELECT AVG(score) FROM scoring WHERE work_id = #{workId}")
    Double getAverageScore(Integer workId);
}
