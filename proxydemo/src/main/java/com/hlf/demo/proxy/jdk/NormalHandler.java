package com.hlf.demo.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理类
 * @author hlf
 * @since 2019/06/21 17:00
 */
public class NormalHandler implements InvocationHandler {

    private Object target;

    public NormalHandler( Object target ) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println( "invoke at:" + System.currentTimeMillis() );
        method.invoke( target, args );
        return null;
    }
}
