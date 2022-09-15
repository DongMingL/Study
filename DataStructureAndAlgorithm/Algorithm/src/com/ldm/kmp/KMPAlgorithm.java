package com.ldm.kmp;

import java.util.Arrays;

/**
 * @author 梁东明
 * 2022/9/12
 * 人生建议：看不懂的方法或者类记得CTRL + 点击 看看源码或者注解
 * 点击setting在Editor 的File and Code Templates 修改
 *
 * kmp算法
 */
public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int[] next = kmpNext(str2);  //求str2的部分匹配值表
        System.out.println(Arrays.toString(next));
        int i = kmpSearch(str1, str2, next);
        if ( i == -1){
            System.out.println("str1字符串没有包含str2");
        }else {
            System.out.println("str2在str1出现的索引位置在：" + i);
        }
    }

    /**
     * 写出kmp搜索算法
     *
     * @param str1 str1
     * @param str2 str2
     * @param next str2的部分匹配值表
     * @return int  str2如果存在str1，就返回其在str1出现的索引，没有就返回-1
     */
    public static int kmpSearch(String str1, String str2, int[] next){

        for (int i = 1,j =0; i < str1.length(); i++) {
            //当dest.charAt(i) !=  dest.charAt(j) 时，我们需要从next[j-1]获取j
            //直到我们发现dest.charAt(i) == dest.charAt(j)满足时，才退出
            //这时kmp算法的核心！！！！！！
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }

            //当dest.charAt(i) == dest.charAt(j)满足时，部分匹配值就+1
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if ( j == str2.length()){
                return i - j + 1;
            }
        }
        return -1;
    }

    //获取一个字符串的（子串）的部分匹配值表
    public static int[] kmpNext(String dest){
        //创建一个next数组保存部分匹配值
        int[] next = new int[dest.length()];

        for (int i = 1,j =0; i < dest.length(); i++) {
            //当dest.charAt(i) !=  dest.charAt(j)满足时，我们需要从next[j-1]获取j
            //直到我们发现dest.charAt(i) == dest.charAt(j)满足时，才退出
            //这时kmp算法的核心！！！！！！
            while( j > 0 && dest.charAt(i) !=  dest.charAt(j)){
                j = next[j-1];
            }

            //当dest.charAt(i) == dest.charAt(j)满足时，部分匹配值就+1
            if ( dest.charAt(i) == dest.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
