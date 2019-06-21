package com.hlf.spring.demo.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloServiceTest {

    @Test
    public void test1() throws Exception {
        /**
         * 1.加载Spring的配置文件
         * 2.取出Bean容器中的实例
         * 3.调用bean方法
         */
        ApplicationContext context = new ClassPathXmlApplicationContext( "spring.xml" );
        HelloService helloService = (HelloService) context.getBean( "helloService" );
        helloService.hello();
    }
}
