package com.itheima.pojo;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Payment {
    private Integer id;
    private Integer registrationId;
    private Integer studentId;
    private BigDecimal amount;
    private String status;
    private LocalDateTime paymentTime;
    private String paymentMethod;
    private String transactionId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    
    private String competitionTitle;
    private String studentName;
    private String studentIdStr;
}
