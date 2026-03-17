package com.itheima.pojo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Message {
    private Integer id;
    private String title;
    private String content;
    private String type;
    private String source;
    private String targetRole;
    private Integer targetUserId;
    private Integer isRead;
    private LocalDateTime createTime;
    
    private String targetUserName;
}
