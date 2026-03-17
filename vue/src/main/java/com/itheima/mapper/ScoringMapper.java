package com.itheima.mapper;

import com.itheima.pojo.Scoring;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface ScoringMapper {

    @Select("SELECT * FROM scoring WHERE id = #{id}")
    Scoring findById(Integer id);

    List<Scoring> list(Integer workId, Integer scorerId, Integer page, Integer pageSize);

    void insert(Scoring scoring);

    void update(Scoring scoring);

    void delete(Integer id);

    @Select("SELECT * FROM scoring WHERE work_id = #{workId} AND scorer_id = #{scorerId}")
    Scoring findByWorkAndScorer(Integer workId, Integer scorerId);

    @Select("SELECT * FROM scoring WHERE scorer_id = #{scorerId}")
    List<Scoring> findByScorerId(Integer scorerId);
}
