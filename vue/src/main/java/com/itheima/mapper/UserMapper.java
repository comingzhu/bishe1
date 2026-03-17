package com.itheima.mapper;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUsername(String username);

    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(Integer id);

    @Insert("INSERT INTO user (username, password, real_name, role, email, phone, student_id, work_id, id_card, department, major, grade, clazz, status) " +
            "VALUES (#{username}, #{password}, #{realName}, #{role}, #{email}, #{phone}, #{studentId}, #{workId}, #{idCard}, #{department}, #{major}, #{grade}, #{clazz}, #{status})")
    void insert(User user);
}
