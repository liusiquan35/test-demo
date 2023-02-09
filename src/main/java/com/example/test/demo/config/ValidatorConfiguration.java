package com.example.test.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;

import javax.validation.Validation;

@Slf4j
public class ValidatorConfiguration {
    public ValidatorConfiguration() {
        log.debug("创建配置类:ValidatorConfiguration");
    }

    @Bean
    public javax.validation.Validator validator(){
        /*省性能的快速失败方法*/
        return Validation.byProvider(HibernateValidator.class)
                .configure()//开始配置validator
                .failFast(true)//楷亏啊苏失败,即检查请求参数发现错误是直接视为失败,并不向后继续检查
                .buildValidatorFactory()
                .getValidator();
    }
}
