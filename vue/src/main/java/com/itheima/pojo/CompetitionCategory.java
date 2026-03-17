package com.itheima.pojo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CompetitionCategory {
    private Integer id;
    private String name;
    private String description;
    private LocalDateTime createTime;
}
