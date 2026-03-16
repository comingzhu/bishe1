package com.itheima.pojo;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Competition {
    private Integer id;
    private String title;
    private String description;
    private Integer categoryId;
    private String competitionType;
    private Integer publisherId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime registrationStartTime;
    private LocalDateTime registrationEndTime;
    private Integer maxParticipants;
    private BigDecimal fee;
    private String location;
    private String status;
    private Integer needWork;
    private Integer needScoring;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    
    private String categoryName;
    private String publisherName;
}
