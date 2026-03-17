package com.itheima.controller;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Payment;
import com.itheima.pojo.Result;
import com.itheima.service.PaymentService;
import com.itheima.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public Result list(@RequestParam(required = false) Integer registrationId,
                      @RequestParam(required = false) Integer studentId,
                      @RequestParam(required = false) String status,
                      @RequestParam(defaultValue = "1") Integer page,
                      @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("查询缴费列表：registrationId={}, studentId={}, status={}", registrationId, studentId, status);
        PageResult<Payment> pageResult = paymentService.list(registrationId, studentId, status, page, pageSize);
        return Result.success(pageResult);
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        log.info("查询缴费详情：{}", id);
        Payment payment = paymentService.findById(id);
        return Result.success(payment);
    }

    @PostMapping
    public Result createPayment(@RequestBody Payment payment) {
        log.info("创建缴费：registrationId={}, amount={}", payment.getRegistrationId(), payment.getAmount());
        
        Integer currentUserId = CurrentHolder.getCurrentId();
        payment.setStudentId(currentUserId);
        
        paymentService.createPayment(payment);
        return Result.success("创建成功");
    }

    @PutMapping("/{id}/pay")
    public Result pay(@PathVariable Integer id,
                    @RequestParam String paymentMethod,
                    @RequestParam String transactionId) {
        log.info("缴费：id={}, paymentMethod={}", id, paymentMethod);
        paymentService.pay(id, paymentMethod, transactionId);
        return Result.success("缴费成功");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("删除缴费：{}", id);
        paymentService.delete(id);
        return Result.success("删除成功");
    }
}
