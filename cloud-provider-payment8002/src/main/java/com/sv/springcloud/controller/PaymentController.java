package com.sv.springcloud.controller;

import com.sv.springcloud.entities.CommonResult;
import com.sv.springcloud.entities.Payment;
import com.sv.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("*****insert result: "+result);

        if(result>0){
            return new CommonResult(200,"success to insert, serverPort: " + serverPort,result);
        }else{
            return  new CommonResult(444, "fall to insert", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("***** search result: "+payment);

        if(payment !=null){
            return new CommonResult(200,"success to search, serverPort: " + serverPort,payment);
        }else{
            return  new CommonResult(444, "fall to search, id is: "+id, null);
        }
    }
}
