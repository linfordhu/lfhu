package com.hlf.demo.cyclicbarrier;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 银行交易流水服务类
 * @author hlf
 * @since 2019/06/26
 */
public class BankWaterService implements Runnable {

    /**
     * 创建4个屏障，处理完之后，执行当前类的run方法
     */
    private CyclicBarrier cyclicBarrier = new CyclicBarrier(4, this);

    /**
     * 启动4个线程
     */
    private Executor executor = Executors.newFixedThreadPool( 4 );

    /**
     * 保存每个sheet计算出来的银行交易流水结果
     */
    private ConcurrentHashMap<String, Integer> sheetBankWaterCount = new ConcurrentHashMap<String, Integer>();

    private void count() {
        for(int i = 0; i < 8; i ++) {
            executor.execute(new Runnable() {
                public void run() {
                    sheetBankWaterCount.put( Thread.currentThread().getName(), 1 );
                    System.out.println( Thread.currentThread().getName() + "执行" );
                    try{
                        cyclicBarrier.await();
                    } catch(InterruptedException e) {
                        e.printStackTrace();;
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * 汇总计算结果
     */
    public void run() {
        int result = 0;
        for ( Map.Entry<String, Integer> sheet: sheetBankWaterCount.entrySet() ) {
            result += sheet.getValue();
        }
        //设置计算结果
        sheetBankWaterCount.put( "result", result );
        System.out.println( result );
    }

    public static void main( String[] args ) {
        BankWaterService service = new BankWaterService();
        service.count();
    }

}
