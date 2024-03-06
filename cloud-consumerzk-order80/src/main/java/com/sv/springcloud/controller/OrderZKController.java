package com.sv.springcloud.controller;

import com.sv.springcloud.entities.CommonResult;
import com.sv.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author Owen
 */
@RestController
@Slf4j
public class OrderZKController {
    //public static final String PAYMENT_URL = "http://localhost:8001";

    // 通过在eureka上注册过的微服务名称调用
    public static final String PAYMENT_URL = "http://cloud-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/zk")
    public String getPayment(){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/zk", String.class);
    }
}
