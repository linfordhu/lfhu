package com.hlf.demo.proxy.jdk;

import com.hlf.demo.proxy.IPerson;
import com.hlf.demo.proxy.Man;

import java.lang.reflect.Proxy;

/**
 * man proxy test
 * @author hlf
 * @since 2019/06/21 17:10
 */
public class ManProxy {

    public static void main( String[] args ) {
        Man man = new Man();
        NormalHandler normalHandler = new NormalHandler(man);
        IPerson iPerson = (IPerson) Proxy.newProxyInstance(IPerson.class.getClassLoader(),
                new Class[] {IPerson.class}, normalHandler);
        iPerson.say();
    }
}
