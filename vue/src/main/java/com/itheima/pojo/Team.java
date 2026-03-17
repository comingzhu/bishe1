package com.itheima.pojo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Team {
    private Integer id;
    private Integer competitionId;
    private String name;
    private Integer leaderId;
    private Integer maxMembers;
    private String status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    
    private String competitionTitle;
    private String leaderName;
    private Integer currentMembers;
}
