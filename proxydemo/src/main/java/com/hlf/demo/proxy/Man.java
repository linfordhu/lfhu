package com.hlf.demo.proxy;

/**
 * 男
 * @author hlf
 * @since 2019/06/21
 */
public class Man implements IPerson {

    @Override
    public void say() {
        System.out.println( "man say" );
    }
}
