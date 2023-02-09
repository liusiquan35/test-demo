package com.example.test.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**Mybatis的配置类
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Configuration//配置类声明
@MapperScan("cn.tedu.csmall.product.mapper")//代替@Mapper
public class MybatisConfiguration {

}
