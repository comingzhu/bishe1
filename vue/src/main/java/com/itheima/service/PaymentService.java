package com.itheima.service;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Payment;

public interface PaymentService {
    
    PageResult<Payment> list(Integer registrationId, Integer studentId, String status, Integer page, Integer pageSize);
    
    Payment findById(Integer id);
    
    void createPayment(Payment payment);
    
    void pay(Integer id, String paymentMethod, String transactionId);
    
    void delete(Integer id);
}
