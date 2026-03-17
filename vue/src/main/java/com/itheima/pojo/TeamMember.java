package com.itheima.pojo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TeamMember {
    private Integer id;
    private Integer teamId;
    private Integer studentId;
    private LocalDateTime joinTime;
    private String role;
    
    private String studentName;
    private String studentIdStr;
}
