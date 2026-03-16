package com.itheima.pojo;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Scoring {
    private Integer id;
    private Integer workId;
    private Integer scorerId;
    private BigDecimal score;
    private String comment;
    private LocalDateTime scoringTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    
    private String workTitle;
    private String scorerName;
    private String studentName;
    private String teamName;
}
