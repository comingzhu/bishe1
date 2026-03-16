package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.PaymentMapper;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Payment;
import com.itheima.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public PageResult<Payment> list(Integer registrationId, Integer studentId, String status, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Payment> list = paymentMapper.list(registrationId, studentId, status, page, pageSize);
        Page<Payment> p = (Page<Payment>) list;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Override
    public Payment findById(Integer id) {
        return paymentMapper.findById(id);
    }

    @Override
    public void createPayment(Payment payment) {
        payment.setStatus("unpaid");
        paymentMapper.insert(payment);
    }

    @Override
    @Transactional
    public void pay(Integer id, String paymentMethod, String transactionId) {
        Payment payment = new Payment();
        payment.setId(id);
        payment.setStatus("paid");
        payment.setPaymentTime(LocalDateTime.now());
        payment.setPaymentMethod(paymentMethod);
        payment.setTransactionId(transactionId);
        paymentMapper.update(payment);
    }

    @Override
    public void delete(Integer id) {
        paymentMapper.delete(id);
    }
}
