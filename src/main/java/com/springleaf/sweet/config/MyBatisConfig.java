package com.springleaf.sweet.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.springleaf.sweet.mapper")
public class MyBatisConfig {
}