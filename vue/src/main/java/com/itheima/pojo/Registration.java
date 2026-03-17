package com.itheima.pojo;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Registration {
    private Integer id;
    private Integer competitionId;
    private Integer studentId;
    private Integer teamId;
    private LocalDateTime registrationTime;
    private String status;
    private LocalDateTime auditTime;
    private Integer auditorId;
    private String remark;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    
    private String competitionTitle;
    private String studentName;
    private String studentIdStr;
    private String teamName;
    private BigDecimal fee;
}
