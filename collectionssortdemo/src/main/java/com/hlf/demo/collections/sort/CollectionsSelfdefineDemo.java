package com.hlf.demo.collections.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionsSelfdefineDemo {
    private static List<Integer> intList = Arrays.asList( 2, 3, 1 );

    public static void main( String[] args ) {
        System.out.println( "before sort" );
        System.out.println( intList.toString() );
        Collections.sort(intList, new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                //返回值为int类型，大于0表示正序，小于0表示逆序
                return o2-o1;
            }
        });
        System.out.println( "after sort" );
        System.out.println( intList.toString() );
    }
}
