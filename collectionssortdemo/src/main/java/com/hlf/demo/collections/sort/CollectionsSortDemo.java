package com.hlf.demo.collections.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CollectionsSortDemo {

    private static List<Integer> intList = Arrays.asList( 2, 3, 1 );

    public static void main( String[] args ) {
        System.out.println( "before sort" );
        System.out.println( intList.toString() );
        Collections.sort( intList );
        System.out.println( "after sort" );
        System.out.println( intList.toString() );
    }

}
