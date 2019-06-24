package com.hlf.demo.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureCook {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long startTime = System.currentTimeMillis();
        //第一步 网购厨具
        Callable<Chuju> onlineShopping = new Callable<Chuju>() {

            @Override
            public Chuju call() throws Exception {
                System.out.println( "第一步：下单" );
                System.out.println( "第一步：等待送货" );
                // 模拟送货时间
                Thread.sleep( 5000 );
                System.out.println( "第一步：快递送到" );
                return new Chuju();
            }
        };
        FutureTask<Chuju> task = new FutureTask<Chuju>(onlineShopping);
        new Thread(task).start();
        //第二步 去超市购买食材
        Thread.sleep( 2000 );
        Shicai shicai = new Shicai();
        System.out.println( "第二步：食材到位" );
        //第三步 用厨具烹饪食材
        if( !task.isDone() ) {
            System.out.println( "第三步：厨具还没到，心情好就等着" );
        }
        Chuju chuju = task.get();
        System.out.println( "第三步：厨具到位" );
        System.out.println( "总共用时" + (System.currentTimeMillis() - startTime) + "ms" );
    }
}
