package com.itheima.mapper;

import com.itheima.pojo.Competition;
import com.itheima.pojo.CompetitionVO;
import com.itheima.pojo.CompetitionQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface CompetitionMapper {

    @Select("SELECT * FROM competition WHERE id = #{id}")
    Competition findById(Integer id);

    List<CompetitionVO> list(CompetitionQueryParam param);

    void insert(Competition competition);

    void update(Competition competition);

    void delete(Integer id);
}
