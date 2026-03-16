package com.itheima.pojo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String realName;
    private String role;
    private String email;
    private String phone;
    private String studentId;
    private String workId;
    private String idCard;
    private String department;
    private String major;
    private String grade;
    private String clazz;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
