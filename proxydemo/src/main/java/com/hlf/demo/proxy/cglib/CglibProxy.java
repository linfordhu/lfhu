package com.hlf.demo.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib实现动态代理
 * @author hlf
 * @since 2019/06/21 17:09
 */
public class CglibProxy implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class clazz) {
        //设置需要创建子类
        enhancer.setSuperclass( clazz );
        enhancer.setCallback( this );
        //通过字节码技术动态创建子类实例
        return enhancer.create();
    }

    /**
     * 实现intercept方法
     * @param o o
     * @param method method
     * @param objects objects
     * @param methodProxy methodProxy
     * @return Object
     * @throws Throwable Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("前置代理");
        //通过代理类调用父类中的方法
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("后置代理");
        return result;
    }
}
