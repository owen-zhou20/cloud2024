package com.sv.springcloud.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    // ok
    public String paymentInfo_OK(Integer id){
        return "Thread Pool:"+Thread.currentThread().getName()+", paymentInfo_OK,id: "+id+"\t"+"O(∩_∩)O";
    }

    // error: timeout
    public String paymentInfo_TimeOut(Integer id) {
        int timeNumber = 3;
        try { TimeUnit.SECONDS.sleep(timeNumber); } catch (InterruptedException e) { e.printStackTrace(); }
        return "Thread Pool:"+Thread.currentThread().getName()+", paymentInfo_TimeOut,id: "+id+"\t"+"O(∩_∩)O，total(Seconds): "+timeNumber;
    }


}
