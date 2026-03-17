package com.itheima.mapper;

import com.itheima.pojo.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface PaymentMapper {

    @Select("SELECT * FROM payment WHERE id = #{id}")
    Payment findById(Integer id);

    List<Payment> list(Integer registrationId, Integer studentId, String status, Integer page, Integer pageSize);

    void insert(Payment payment);

    void update(Payment payment);

    void delete(Integer id);

    @Select("SELECT * FROM payment WHERE registration_id = #{registrationId}")
    Payment findByRegistrationId(Integer registrationId);

    @Select("SELECT * FROM payment WHERE student_id = #{studentId}")
    List<Payment> findByStudentId(Integer studentId);
}
