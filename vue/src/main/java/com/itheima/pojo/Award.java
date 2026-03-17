package com.itheima.pojo;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Award {
    private Integer id;
    private Integer competitionId;
    private Integer registrationId;
    private Integer studentId;
    private Integer teamId;
    private String awardLevel;
    private String awardName;
    private BigDecimal score;
    private Integer ranking;
    private LocalDateTime awardTime;
    private LocalDateTime createTime;
    
    private String competitionTitle;
    private String studentName;
    private String teamName;
}
