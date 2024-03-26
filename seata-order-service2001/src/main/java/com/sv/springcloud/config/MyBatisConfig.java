package com.sv.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan({"com.sv.springcloud.dao"})
public class MyBatisConfig {
}


