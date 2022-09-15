package com.ldm.kmp;

import java.util.Arrays;

/**
 * @author 梁东明
 * 2022/9/12
 * 人生建议：看不懂的方法或者类记得CTRL + 点击 看看源码或者注解
 * 点击setting在Editor 的File and Code Templates 修改
 */
public class MyKMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "caudicgbahvbajkccb";
        String str2 = "cb";
        int[] next = kmpNext(str2);
        System.out.println("部分匹配表 = " + Arrays.toString(next));

        int i = kmpAlgorithm(str1, str2, next);
        if ( i == -1){
            System.out.println("str1没有包含str2");
        }else {
            System.out.println("str2在str1出现的索引是 = " + i);
        }
    }

    public static int kmpAlgorithm(String str1,String str2, int[] next){
        for (int i = 0 ,j =0; i < str1.length(); i++) {
            while ( j > 0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j-1];
            }
            if (str1.charAt(i) == str2.charAt(j)){
                j++;
            }
            if ( j == str2.length()){
                return i - j + 1;
            }
        }
        return -1;
    }

    public static int[] kmpNext(String dest){
        int[] next = new int[dest.length()];
        for (int i = 1, j= 0; i < dest.length(); i++) {
            while (j > 0 && dest.charAt(i) != dest.charAt(j)){
                j = next[j-1];
            }
            if (dest.charAt(i) == dest.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
