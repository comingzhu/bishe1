package com.itheima.mapper;

import com.itheima.pojo.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface MessageMapper {

    @Select("SELECT * FROM message WHERE id = #{id}")
    Message findById(Integer id);

    List<Message> list(String targetRole, Integer targetUserId, Integer isRead, Integer page, Integer pageSize);

    void insert(Message message);

    void update(Message message);

    void delete(Integer id);

    @Select("SELECT * FROM message WHERE target_user_id = #{targetUserId} ORDER BY create_time DESC")
    List<Message> findByTargetUserId(Integer targetUserId);

    @Select("SELECT COUNT(*) FROM message WHERE target_user_id = #{targetUserId} AND is_read = 0")
    Integer countUnread(Integer targetUserId);
}
