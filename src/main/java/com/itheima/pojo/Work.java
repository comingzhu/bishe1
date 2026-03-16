package com.itheima.pojo;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Work {
    private Integer id;
    private Integer competitionId;
    private Integer registrationId;
    private Integer studentId;
    private Integer teamId;
    private String title;
    private String description;
    private String fileUrl;
    private String fileName;
    private LocalDateTime submitTime;
    private String status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    
    private String competitionTitle;
    private String studentName;
    private String teamName;
    private BigDecimal averageScore;
}
