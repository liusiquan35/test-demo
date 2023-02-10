package com.example.test.demo.config;


import com.example.test.demo.mybatis.InsertUpdateTimeInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

/**Mybatis的配置类
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Slf4j
@Configuration//配置类声明
@MapperScan("com.example.test.demo.mapper")//代替@Mapper
public class MybatisConfiguration {
    public MybatisConfiguration() {
        log.debug("创建配置类对象：MybatisConfiguration");
    }

    @Autowired
    private List<SqlSessionFactory> sqlSessionFactoryList;

    @PostConstruct // 使得此方法在调用了构造方法、完成了属性注入之后自动执行
    public void addInterceptor() {
        //自动添加创建时间和更新时间
        InsertUpdateTimeInterceptor interceptor = new InsertUpdateTimeInterceptor();
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            sqlSessionFactory.getConfiguration().addInterceptor(interceptor);
        }
    }

}
