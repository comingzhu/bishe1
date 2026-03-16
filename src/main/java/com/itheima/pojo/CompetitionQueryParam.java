package com.itheima.pojo;

import lombok.Data;

@Data
public class CompetitionQueryParam {
    private String title;
    private Integer categoryId;
    private String competitionType;
    private String status;
    private Integer publisherId;
    private Integer page;
    private Integer pageSize;
}
