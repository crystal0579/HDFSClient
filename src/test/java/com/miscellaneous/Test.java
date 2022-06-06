package com.miscellaneous;

import java.math.BigDecimal;

/**
 * 适合使用 string
 */
public class Test {
    public static void main(String[] args){
        BigDecimal bigDecimal = new BigDecimal(88);
        System.out.println(bigDecimal);
        bigDecimal = new BigDecimal("8.8");
        System.out.println(bigDecimal);
        bigDecimal = new BigDecimal(8.8);
        System.out.println(bigDecimal);
    }
}
