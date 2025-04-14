package com.connordev.spending_tracking_be.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Configuration
public class BeanConfig {

    @Bean
    public MapperFactory factoryMapper() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder()
            .build();
        return mapperFactory;
    }
}
