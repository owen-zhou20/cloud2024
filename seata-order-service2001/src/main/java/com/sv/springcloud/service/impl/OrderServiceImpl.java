package com.sv.springcloud.service.impl;

import com.sv.springcloud.dao.OrderDao;
import com.sv.springcloud.domain.Order;
import com.sv.springcloud.service.AccountService;
import com.sv.springcloud.service.OrderService;
import com.sv.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{

    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;

    @Override
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("--------> 下单开始");
        orderDao.create(order);

        log.info("--------> storage-service中扣减库存Count，开始");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("--------> storage-service中扣减库存Count，结束");

        log.info("--------> account-service中扣减账户余额Money，开始");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("--------> account-service中扣减账户余额Money，结束");

        log.info("--------> 修改订单状态，开始");
        orderDao.update(order.getUserId(),0);
        log.info("--------> 修改订单状态，结束");

        log.info("--------> 下单结束，O(∩_∩)O哈哈~");


    }
}
