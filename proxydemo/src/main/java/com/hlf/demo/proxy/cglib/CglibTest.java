package com.hlf.demo.proxy.cglib;

import com.hlf.demo.proxy.Man;

public class CglibTest {

    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        //通过生成子类的方式创建代理类
        Man man = (Man)proxy.getProxy(Man.class);
        man.say();
    }
}
